//Linjian Li

import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;





public class analyzeVicSex {

  public static class vSexMapper
      extends Mapper<LongWritable, Text, Text, IntWritable> {
      
    private Text word = new Text();
    private IntWritable onee = new IntWritable(1);

    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {

        String holder [] = value.toString().split(",");

        String location = "";


        if (holder.length == 21) {
          try {

            if (holder[14] != "") {
              location = holder[14];
            }

            else {
              
            }

            if (location.equals("M")) {
              String s1 = "MALE";
              context.write(new Text(s1), onee);
            }

            else if (location.equals("F")) {
              String s2 = "FEMALE";
              context.write(new Text(s2), onee);
            }

            else{

            }

          } catch (Exception e) {
              System.err.println(e);
          }
        }
        else {

        }
      
    }
  }

  public static class vSexReducer
      extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for (IntWritable value : values) {
            sum = sum + value.get();
        }
        context.write(key, new IntWritable(sum));
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "analyzeVicSex");
    job.setJarByClass(analyzeVicSex.class);
    job.setNumReduceTasks(1);

    job.setMapperClass(vSexMapper.class);
    //job.setCombinerClass(boroReducer.class);
    job.setReducerClass(vSexReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

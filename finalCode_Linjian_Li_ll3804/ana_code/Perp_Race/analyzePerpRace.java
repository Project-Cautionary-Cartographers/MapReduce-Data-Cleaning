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





public class analyzePerpRace {

  public static class pRaceMapper
      extends Mapper<LongWritable, Text, Text, IntWritable> {
      
    private Text word = new Text();
    private IntWritable onee = new IntWritable(1);

    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {

        String holder [] = value.toString().split(",");

        String location = "";


        if (holder.length == 21) {
          try {

            if (holder[12] != "") {
              location = holder[12];
            }

            else {
              
            }

            if (location.equals("BLACK")) {
              String s1 = "BLACK";
              context.write(new Text(s1), onee);
            }

            else if (location.equals("WHITE HISPANIC")) {
              String s2 = "WHITE HISPANIC";
              context.write(new Text(s2), onee);
            }

            else if (location.equals("BLACK HISPANIC")) {
              String s3 = "BLACK HISPANIC";
              context.write(new Text(s3), onee);
            }

            else if (location.equals("ASIAN / PACIFIC ISLANDER")) {
              String s4 = "ASIAN / PACIFIC ISLANDER";
              context.write(new Text(s4), onee);
            }

            else if (location.equals("WHITE")) {
              String s5 = "WHITE";
              context.write(new Text(s5), onee);
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

  public static class pRaceReducer
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
    Job job = Job.getInstance(conf, "analyzePerpRace");
    job.setJarByClass(analyzePerpRace.class);
    job.setNumReduceTasks(1);

    job.setMapperClass(pRaceMapper.class);
    //job.setCombinerClass(boroReducer.class);
    job.setReducerClass(pRaceReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

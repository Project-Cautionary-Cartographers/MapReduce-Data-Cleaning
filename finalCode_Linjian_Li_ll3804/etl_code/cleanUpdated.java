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



public class cleanUpdated {

  public static class CleanMapper
      extends Mapper<Object, Text, Text, Text> {
      
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

        String holder [] = value.toString().split(",");


        if (holder.length == 21) {
          try {

            context.write(word, value);

          } catch (Exception e) {
              System.err.println(e);
          }
        }
        else {

        }      
    }
  }

  public static class CleanReducer
      extends Reducer<Object, Text, Text, Text> {

    public void reduce(Text key, Text values,
                        Context context
                        ) throws IOException, InterruptedException {

      context.write(key, values);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "cleanUpdated");
    job.setJarByClass(cleanUpdated.class);
    job.setNumReduceTasks(1);

    job.setMapperClass(CleanMapper.class);
    job.setCombinerClass(CleanReducer.class);
    job.setReducerClass(CleanReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

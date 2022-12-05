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



public class analyzeCoordinate {

    public static class coorMapper
      extends Mapper<Object, Text, Text, Text> {
      
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

        String holder [] = value.toString().split(",");

        String id = "";
        String date = "";
        String location = "";
        String latitude = "";
        String longitude = "";


        if (holder.length == 21) {
          try {

            if (holder[0] != "") {
              id = holder[0];
            }

            if (holder[1] != "") {
              date = holder[1];
            }

            if (holder[3] != "") {
              location = holder[3];
            }

            if (holder[18] != "") {
              latitude = holder[18];
            }

            if (holder[19] != "") {
              longitude = holder[19];
            }

            else {
              latitude = "No data";
              longitude = "No data";
            }

            context.write(word, new Text(id + "," + date + "," + location + "," + latitude + "," + longitude));

          } catch (Exception e) {
              System.err.println(e);
          }
        }
        else {

        }
    }
  }



  public static class coorReducer
      extends Reducer<Object, Text, Text, Text> {

    public void reduce(Text key, Text values,
                        Context context
                        ) throws IOException, InterruptedException {

      context.write(key, values);
    }
  }



  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "analyzeCoordinate");
    job.setJarByClass(analyzeCoordinate.class);
    job.setNumReduceTasks(1);

    job.setMapperClass(coorMapper.class);
    job.setCombinerClass(coorReducer.class);
    job.setReducerClass(coorReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

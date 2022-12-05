import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.*;

import java.io.IOException;
import java.io.StringReader;


public class CountKYCD extends Configured implements Tool {

    public static class CountKYCDMap extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            CSVReader reader = new CSVReader(new StringReader(value.toString()));
            try {
                String[] items = reader.readNext();
                String addr_code = items[12] + " " + items[15] + ":";
                context.write(new Text(addr_code), one);
            } catch (CsvValidationException e) {
                return;
            }
        }
    }

    private static class CountKYCDReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    public int run(String[] args) throws Exception {
        // Configuration processed by ToolRunner
        Configuration conf = getConf();
        // conf.set("fs.defaultFS", "hdfs://localhost:9015");
        // Create a Job
        Job job = Job.getInstance(conf, "CountKYCD");
        job.addFileToClassPath(new Path("lib/opencsv-5.7.1.jar"));
        // Process custom command-line options
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        // Specify various job-specific parameters 
        job.setNumReduceTasks(1);
        job.setJarByClass(CountKYCD.class);
        job.setMapperClass(CountKYCDMap.class);
        job.setReducerClass(CountKYCDReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, in);
        FileOutputFormat.setOutputPath(job, out);
        // Submit the job, then poll for progress until the job is complete
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
         // Let ToolRunner handle generic command-line options 
         int res = ToolRunner.run(new Configuration(), new CountKYCD(), args);
         System.exit(res);
    }
}

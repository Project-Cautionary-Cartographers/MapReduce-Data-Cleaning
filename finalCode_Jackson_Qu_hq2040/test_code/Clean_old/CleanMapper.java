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

import java.io.IOException;


public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final static IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] items = line.split(",");
        String newLine = new String();
        for (int i = 0; i < items.length - 1; i++) {
            if (i == 2) {
                continue;
            } else {
                newLine += items[i] + ",";
            }
        }
        newLine += items[items.length - 1];
        context.write(new Text("New line"), new Text(newLine));
    }
}

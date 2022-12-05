import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountRecsMapper
  extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);    
    private Text word = new Text(); 
    public void map(Object key, Text value, Context context
                ) throws IOException, InterruptedException {
        String line = value.toString();
        String [] array = line.split(",");
        Text word2 = new Text("Total number of lines in file: ");
        context.write(word2,one);
        /*
         if(array.length == 16){
            Text word2 = new Text("Total number of good lines in AirBNB file: ");
            context.write(word2, one);
        }
        else{
        System.out.println("not");
        }
         */
    }     
}
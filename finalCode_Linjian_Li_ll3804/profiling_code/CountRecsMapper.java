//Linjian Li

import java.io.IOException;
// import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class CountRecsMapper
    extends Mapper<Object, Text, Text, IntWritable> {
    
  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();

  @Override
  public void map(Object key, Text value, Context context
                  ) throws IOException, InterruptedException {

      word.set("Total number of records: ");
      context.write(word, one);
    
  }
}


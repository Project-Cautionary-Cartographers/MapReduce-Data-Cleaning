import java.io.IOException;
import java.util.StringTokenizer;
//import java.time.LocalDateTime;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CleanMapper
  extends Mapper<Object, Text, Text, Text>{

    private final static IntWritable one = new IntWritable(1);    
    private Text word = new Text(); 
    public void map(Object key, Text value, Context context
                ) throws IOException, InterruptedException {
        String line = value.toString();
        String []arr = line.split(",");
        if(arr.length == 19){
          if(arr[1] != null && arr[2] != null && arr[8] != null && arr[16] != null && arr[17] != null){
            /* LocalDate date = arr[1]; //arrest date maybe change to String or int 
            int code = Integer.parseInt(arr[2]); //arrest code
            String borough = arr[8]; //arrest borough
            double latitude = Double.parseDouble(arr[16]); //latitude
            double longitude = Double.parseDouble(arr[17]); //longitude
            */
            String date = arr[1]; 
            String code = arr[2];
            String borough = arr[8]; 
            String latitude = arr[16]; 
            String longitude = arr[17]; 
            context.write(new Text(date+","+code+","+borough+","+latitude+","+longitude+","),new Text(""));//,new IntWritable(0));
          }
        }
        /*
         TODO list:
         Drop Columns you don't need/ Won't be in the data Schema
         Drop Rows with Null Values 
        Check Max String length/ necessity of String length
        Check Max Min of int values
        Can yes/no columns be booleans or do they have other values?
        Doubles? Decimals?
         */
    }     
}

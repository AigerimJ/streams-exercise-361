package kz.edu.nu.cs.teaching;

import java.awt.List;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author marks1024
 *
 */
public class App {
    
  static List words = new List();
  static ArrayList<String> arrayList = new ArrayList<String>();
//  private String[] wwwwoooords;
	
    public static void main(String[] args) {
        Stream<String> s = getTestLinesStream();
        System.out.println("\nTask 1: \n");

        //:Task 1:
        Long wordsCount = s.flatMap(str->Stream.of(str.split("[ ,.!?\r\n]")))
        	    .filter(a->a.length()>0).count();
        	System.out.println(wordsCount);
        
        //:Task 2:
        // it also works :)	
//        s.forEach( item -> {
//            String[] arr = item.split(" ");    
//            for ( String ss : arr) {
//            	if (ss.length() > 7) {
//        			System.out.println(ss);
//            	}   
//            }        	      	
//        });        	
        
            //:Task 2:

        Stream<String> s1 = getTestLinesStream();
        Stream<String> s2 = getTestLinesStream();

        System.out.println("\nTask 2: \n");
 
      s1.flatMap(str->Stream.of(str.split("[ ,.!?\r\n]"))).flatMap(word->Stream.of(word.split(" ")))
		.filter(a->a.length()>7)
		.forEach(w -> {
			arrayList.add(w);
		});
      
      s2.forEach(line -> {
			
			for (String ss : arrayList) {
				if (line.contains(ss)) {
        			System.out.println(line);
            	}   
			}

		});

         
      
      // Task 3:
      Stream<String> s3 = getTestLinesStream();
      System.out.println("\nTask 3: \n");

      Map<Character, Long> map = s3.flatMap(ss -> Stream.of(ss.split("[^a-zA-Z]"))).
                                  filter(ss -> ss.length() > 0).
                                  map(ss -> ss.toLowerCase()).
                                  collect(Collectors.groupingBy(ss -> ss.toString().charAt(0), Collectors.counting()));
      System.out.println(map.toString());

      s3.close();
//        s.forEach(System.out::println);
        
        s.close();
        s1.close();
        s2.close();

    }
    
    public static Stream<String> getTestLinesStream() {
        File file = new File("lambtest.txt");
        
        try {
            return Files.lines(file.toPath());
        } catch (Exception e) {
            System.out.println("Error reading from file");
            return null;
        }
    }
}

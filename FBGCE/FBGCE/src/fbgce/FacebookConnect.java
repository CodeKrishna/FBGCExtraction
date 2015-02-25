package fbgce;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import java.util.ArrayList;
/**
 *
 * @author Debdut
 */
public class FacebookConnect
{
    
    public static void main(String args[])
    {
    FacebookClient fbClient=new DefaultFacebookClient("CAACEdEose0cBADS41QtuDeITVnrH9rZBDkwaZCZCFC8V9i7wcZAIF4F2GMRiWc38N6HZAfv5jpLw5ysSCUs8RFqHeHGKv6FdAZCl9oYqdBXWhWTG7jhhZA6sXbypbhn5yn5D2rDGzeApiP1X3f7Qa6wTVtvwtKwLsGw8IT2TrA3ShZCEqIFvQ9K5X7ealJpRF6whUzh41Jo5jwrjR7wDrZBpqZCczkI6660ZA0ZD", Version.UNVERSIONED);
     int p=0;
    
    JsonObject GroupPostConnection = fbClient.fetchObject("867927643271221/feed", JsonObject.class);
    String UnParsedText = GroupPostConnection.getJsonArray("data").getString(0);
 
    String s1=UnParsedText;
    s1=s1.substring(s1.indexOf("\"comments\""));
    ArrayList <String>arr=new ArrayList<String>();
    
    while(s1.indexOf("\"message\"")!=-1)
    {
     p=s1.indexOf("\"message\"");
    arr.add(s1.substring(p,s1.indexOf(",", p)));
    s1=s1.substring(p+1);
     }
    //String [] arr1 = arr.toArray(new String[arr.size()]);
        
    for(String x:arr.toArray(new String[arr.size()]))
    System.out.println(x);
    
    }
    
    
}

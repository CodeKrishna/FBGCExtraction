<%-- 
    Document   : index
    Created on : Feb 25, 2015, 4:01:19 PM
    Author     : Debdut
--%>


<!--The following are the imports of java packages -->
<%@page import="java.util.ArrayList"%>

<!--The following are the imports of facebook API for java called restfb -->
<%@page import="com.restfb.DefaultFacebookClient"%>
<%@page import="com.restfb.FacebookClient"%>
<%@page import="com.restfb.Version"%>
<%@page import="com.restfb.json.JsonObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facebook Group Post Comment Extraction </title>
    </head>
    <body>
        <h1><center>Facebook Group Post Comment Extraction OUTPUT</center></h1>
        <br>


        <!-- JSP code-->
        <%
            //Creation of a FacebookClient object and passing the facebook Graph API acess token
            //this token expires after 2 hours so must be changed in the code as well.
            //However Once we create a facebook app we will get a app id and app secret which does not change 
            //So we can create a FacebookClient Object that takes app id and app secret so that we don't need to change it every 2 hours
            FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBAE5aJlRP5278wOgqvnvEOgSqTwSbgQqMui7zTbkBrcwkovOI9tYyQWLpGH9d4zj7WdwnIOuD1wQfvBtEzfgK0nGTAMa3yWJWaOxiWTZBYLLiZCCOg5EwKSUZBDZCIZBJoM0inBSc0X3HFo8ZBrqi2YXJRPG4xNvSFEamC1RcWoRvPzCW63IZCua9JUTimAZBTUeBWM27Abt1a5WlDCSHASQZD", Version.UNVERSIONED);
            int p = 0;

            //JsonObject created to fetch the object whose id is 867927643271221 i.e the Group from which we want data
            //867927643271221/feed refers to the group posts
            JsonObject GroupPostConnection = fbClient.fetchObject("867927643271221/feed", JsonObject.class);
            //Extracting the Json output from the part where first occurce of the word "data" is present to the end of output.
            //This output resut is stored in the form of a string.
            //getString(0) refers to the 1st post in the group
            String UnParsedText = GroupPostConnection.getJsonArray("data").getString(0);

            //Below is the logic for parsing of the string to get rid of unwanted string and retaining only the messages.
            //and storing each message in an arraylist
            String s1 = UnParsedText;
            s1 = s1.substring(s1.indexOf("\"comments\""));
            ArrayList<String> arr = new ArrayList<String>();

            while (s1.indexOf("\"message\"") != -1)
            {
                p = s1.indexOf("\"message\"");
                arr.add(s1.substring(p, s1.indexOf(",", p)));
                s1 = s1.substring(p + 1);
            }

            //little more parsing for proper display of the string output messages stored in the array list and displaying them   
            for (String x : arr.toArray(new String[arr.size()]))
            {
                x = x.replaceAll("\"", "");
                x = x.replaceAll(":", ":- ");
                out.println(x);
                out.println("<br><br>");
            }
        %>


    </body>
</html>

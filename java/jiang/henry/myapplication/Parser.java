package jiang.henry.myapplication;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * A class handling the API call to Swapi. Instantialized as a Singleton.
 */
public class Parser {

    // String representing class name, used for debugging purposes.
    private static final String TAG = Parser.class.getSimpleName();

    // Instance of this class
    private static final Parser instance;

    // Default constructor.
    private Parser() {   }

    // Static instantialization of this class
    static{
        try{
            instance = new Parser();
        } catch(Exception e) {
            throw new RuntimeException("Static initialization of parser failed.");
        }
    }

    /**
     * Gets the instance of this class
     * @return the instance of this class
     */
    public static Parser getInstance() {
        return instance;
    }

    /**
     * Makes a service request to the API specified in the parameters.
     * If reponse is received, converts response to string and returns.
     * @param reqUrl a string representing the request URL.
     * @return a string (in JSON format) representing the response.
     */
    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    /**
     * Converts the response from the API call into a string for processing
     *
     * @param is the input stream received from the API call
     * @return a string (in JSON format) representing the response
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            // building the string
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            // error occurred in the inputstream
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // error occurred closing input stream
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }
}

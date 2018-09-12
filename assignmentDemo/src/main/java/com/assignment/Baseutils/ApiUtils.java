package com.assignment.Baseutils;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
/**
 * 
 * @author kkumars
 *
 */
public class ApiUtils extends FrameworkBase {
	
	private static String output = null;
    public static String getOutput() {
		return output;
	}

	public static void setOutput(String output) {
		ApiUtils.output = output;
	}

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		ApiUtils.response = response;
	}

	private static Response response = null;
	
	// for post Response with String request json
	  public String jsonClientPost(String url, String json, String authVal) {
	    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	    final OkHttpClient client = new OkHttpClient();
	    final RequestBody body = RequestBody.create(JSON, json);
	    final Request request = new Request.Builder().url(url).header("Authorization", authVal).post(body).build();
	    try {
	      response = client.newCall(request).execute();
	      if (response.code() != 201) {
	        log("Failed : HTTP error code : " + response.code());
	      }
	      output = response.body().string();
	      log("Response JSON Output is===>> " + output);
	    } catch (final IOException e) {
	      // TODO Auto-generated catch block
	      throw new RuntimeException("Failed : HTTP error code : " + response.code());
	    }
	    return output;
	  }
	  
	// for get Response
	  public String jsonClientGet(String url, String authVal) {
	    final OkHttpClient client = new OkHttpClient();
	    final Request request = new Request.Builder().url(url).header("Authorization", authVal).get().build();
	    try {
	      response = client.newCall(request).execute();
	      if (response.code() != 200) {
	        log("Failed : HTTP error code : " + response.code());
	      }
	      output = response.body().string();
	     } catch (final IOException e) {
	      // TODO Auto-generated catch block
	      throw new RuntimeException("Failed : HTTP error code : " + response.code());
	    }

	    return output;
	  }

}

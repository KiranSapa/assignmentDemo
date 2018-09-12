package apitest.post;

import java.io.IOException;

import org.testng.annotations.Test;

import com.assignment.Baseutils.ApiUtils;
import com.assignment.Baseutils.FrameworkBase;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import assignment.pojo.ResponsePojo;

public class AssignmentApiTest extends FrameworkBase {
	ApiUtils apiUtils = new ApiUtils();
	private static Gson mGson;
	String url = config.getProperty("ApiEndPoint");
	String output;
	int limit;
	String authorization = "foo:bar";
	int code = 0;

	public static Gson getInstance() {
		if (mGson == null) {
			mGson = new Gson();
		}
		return mGson;
	}

	@Test
	public void getStatusCodeValidateMessage() throws IOException {
		// Send post request
		apiUtils.jsonClientGet(url, authorization);
		Response reString = ApiUtils.getResponse();
		log(reString.message());
		// validating the status code:
		code = reString.code();
		getBaseTest().getsoftAssert().assertEquals(code, 401, "Status code not matching:" + reString.message());

		// listing all the headers in logs.
		reString.headers().toMultimap().forEach((a, b) -> log(a + "<-->" + b));

		do {
			output = apiUtils.jsonClientGet(url, authorization);
			limit = Integer.parseInt(ApiUtils.getResponse().headers().get("X-RateLimit-Remaining"));
		} 
		while (limit > 0);
		output = apiUtils.jsonClientGet(url, authorization);
		// validating 403 forbidden
		code = ApiUtils.getResponse().code();
		getBaseTest().getsoftAssert().assertEquals(code, 403, "Status code not matching");

		// De-serializing the output JSON getting the message body
		ResponsePojo rp = getInstance().fromJson(output, ResponsePojo.class);
		log(rp.getMessage());
		log(rp.getDocumentation_url());

		getBaseTest().getsoftAssert().assertAll();

	}

}

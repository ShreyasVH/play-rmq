package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.Http;
import services.QueueService;

import java.util.HashMap;
import java.util.Map;

public class IndexController extends BaseController
{
	private final QueueService queueService;

	@Inject
	public IndexController
	(
		QueueService queueService
	)
	{
		this.queueService = queueService;
	}

	public Result index()
	{
		return ok("INDEX");
	}

	public Result sendFanout(Http.Request request)
	{
		String exchangeName = "";
		String message = "";
		JsonNode requestJson = request.body().asJson();
		if(requestJson.has("exchangeName") && requestJson.has("message"))
		{
			exchangeName = requestJson.get("exchangeName").asText();
			message = requestJson.get("message").asText();
		}

        Boolean success = false;
		try
        {
            success = this.queueService.publish(exchangeName, message, "");
        }
		catch (Exception ex)
        {
            ex.printStackTrace();
        }

		Map<String, Boolean> response = new HashMap<>();
		response.put("success", success);

		return ok(Json.toJson(response));
	}
}
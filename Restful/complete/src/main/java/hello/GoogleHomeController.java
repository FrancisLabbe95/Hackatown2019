package hello;

import org.h2.util.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Fred on 2019-01-19.
 */
@RestController
public class GoogleHomeController {

    private final AtomicLong counter = new AtomicLong();
    private final GoogleHomeService service = new GoogleHomeService();
    private GoogleHomeHelper helper = new GoogleHomeHelper();

  @RequestMapping("/googleHome/Request")
  @ResponseBody
    public String googleHomeIncoming(HttpEntity<String> httpEntity) {
    String jsonString = httpEntity.getBody();
    JSONParser parser = new JSONParser();
    JSONObject json = null;
    double quantity = -1;
    double durationAmount = -1;
    String unit = "";
    try {
      json = (JSONObject) parser.parse(jsonString);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONObject queryRes = (JSONObject) json.get("queryResult");

    if(queryRes.containsKey("parameters")) {
      JSONObject params = (JSONObject) queryRes.get("parameters");
      if(params.containsKey("number")) {
       try{
          quantity = (double) params.get("number");
        }catch (ClassCastException ex){}
      }
      if(params.containsKey("duration")){
        try {
          JSONObject duration = (JSONObject) params.get("duration");
          durationAmount = (double) duration.get("amount");
          unit = (String) duration.get("unit");
        }catch(ClassCastException ex){
        }

      }
    }
    JSONObject intent = (JSONObject) queryRes.get("intent");
    String intentName = (String) intent.get("displayName");
    String answer = answers(intentName, quantity, durationAmount, unit);
    String ret = "{ \"payload\": { \"google\": { \"expectUserResponse\": false, \"richResponse\": { \"items\": [ { \"simpleResponse\": { \"textToSpeech\" : \""+answer+"\"}}]}}}}";
    service.add(new GoogleHomeRequest(counter.incrementAndGet(), answer));
    return ret;
    }
  @RequestMapping("/googleHome")
  @ResponseBody
  public List<GoogleHomeRequest> googleHomeAll(HttpServletRequest request) {
    return service.getAll();
  }

  private String answers(String intentName, double quantity, double durationAmount, String unit){
    String answer = "I did not understand your answer. Sorry.";
    switch (intentName){
      case "Add_bottle":
        if(quantity >=0){
          helper.addBottles((int)quantity);
          answer = (int)quantity + " bottles added. Thank you.";
        }
        else{
          helper.addBottles();
          answer = "Bottles added. Thank you.";
        }
        break;
      case "Remove_bottle":
        if(quantity >=0){
          helper.removeBottles((int)quantity);
          answer = (int)quantity + " bottles removed. Thank you.";
        }
        else{
          helper.removeBottles();
          answer = "Bottles removed. Thank you.";
        }
        break;
      case "Set_available":
        if(durationAmount >=0){
          helper.setAvailable(durationAmount,unit);
          answer = "You are now available for " + durationAmount+ " "+unitConversion(durationAmount,unit);
        }
        else{
          helper.setAvailable();
          answer = "You are now available.";
        }
        break;
      case "Set_unavailable":
        helper.setUnavailable();
        answer = "You are now unavailable";
        break;
    }
    return answer;
  }

  private String unitConversion(double durationAmount, String unit){
    String result = "Hour";
    switch (unit){
      case "h":
        if(durationAmount > 1){
          result = "Hours";
        }
        else{
          result = "Hour";
        }
        break;
      case "m":
        if(durationAmount > 1){
          result = "Minutes";
        }
        else{
          result = "Minute";
        }
        break;
    }
    return result;
  }
}

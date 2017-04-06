package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.Calc.Answer;
import univ.lecture.riotapi.Calc.CalcApp;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1")
@Log4j
public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${riot.api.endpoint}")
    private String riotApiEndpoint;

    @Value("${riot.api.key}")
    private String riotApiKey;

    /*
    @RequestMapping(value = "/summoner/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Summoner querySummoner(@PathVariable("name") String summonerName) throws UnsupportedEncodingException {
        final String url = riotApiEndpoint + "/summoner/by-name/" +
                summonerName +
                "?api_key=" +
                riotApiKey;

        String response = restTemplate.getForObject(url, String.class);
        Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);

        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));

        Map<String, Object> summonerDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
        String queriedName = (String)summonerDetail.get("name");
        int queriedLevel = (Integer)summonerDetail.get("summonerLevel");
        Summoner summoner = new Summoner(queriedName, queriedLevel);

        return summoner;
    }
    */

    @RequestMapping(value = "/calc" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Answer calc(@RequestParam(value = "expression") String expression) {
        double result;
        long currentTime = System.currentTimeMillis();
        CalcApp app = new CalcApp(expression);
        result = app.calc3();
        Answer answer = new Answer(12, currentTime,result);
        return answer;
    }

}

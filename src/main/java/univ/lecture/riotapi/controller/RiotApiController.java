package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.Calc.Answer;
import univ.lecture.riotapi.Calc.CalcApp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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

    @RequestMapping(value = "/calc" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Answer calc(@RequestParam(value = "expression") String expression) {
        double result;
        long currentTime = System.currentTimeMillis();
        CalcApp app = new CalcApp(expression);
        result = app.calc3();
        Answer answer = new Answer(12, currentTime,result);

        try {
            URL url = new URL("https://demo2446904.mockable.io/api/v1/answer");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            JSONObject data = new JSONObject();
            data.put("teamId",answer.getTeamId());
            data.put("now", answer.getNow());
            data.put("result", answer.getResult());

            OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());

            wr.write(data.toString());
            wr.flush();

            StringBuilder sb = new StringBuilder();

            int HttpResult =con.getResponseCode();

            if(HttpResult ==HttpURLConnection.HTTP_OK ){

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                br.close();

                System.out.println(""+sb.toString());

            }else{
                System.out.println(con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            System.out.println("The URL address is incorrect.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("It can't connect to the web page.");
            e.printStackTrace();
        }
        return answer;
    }
}

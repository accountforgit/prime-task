package com.prime.task.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prime.task.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("apiService")
class ApiRemoteService implements RemoteService{

    @Value("${task.baseUrl}")
    String baseUrl;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;
    private Integer caseId=0;

    @Autowired
    public ApiRemoteService(RestTemplate rt,ObjectMapper om){
        restTemplate=rt;
        mapper=om;
    }

    @Override
    public List<DataModel> getData() {
        String startDate=new Date().toString();
        String weather = restTemplate.getForObject(baseUrl, String.class);
        String endDate=new Date().toString();
        try {
            JsonNode node = mapper.readTree(weather);
            Iterator<JsonNode> list = node.get("list").elements();
            ArrayList<DataModel> dataModels = new ArrayList<>();
            while (list.hasNext()){
                list.next();
                dataModels.add(new DataModel((caseId++)+"",new Date().toString(),startDate,endDate,"51.12","42,42","open wheater"," Almaty source"));
            }
            return dataModels;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }
}

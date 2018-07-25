package com.prime.task.services;

import com.prime.task.Root;
import com.prime.task.DataModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.prime.task.TaskApplication.HEADERS;

@Service("fileService")
class FileRemoteService implements RemoteService{

    @Value("classpath:shared/Source2_RR_911_Calls.csv")
    private File scvSource;
    @Value("classpath:shared/Source1_RR_311_Calls.xml")
    private File xmlSource;
    @Override
    public List<DataModel> getData() {
        try {
            Reader in = new FileReader(scvSource);
            ArrayList<DataModel> dataModels = new ArrayList<>();
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                String caseId = record.get("master_incident_number");
                String insertDate= record.get("response_date");
                String startDate= record.get("StartDate");
                String endDate= record.get("EndDate");
                String latitude= record.get("latitude");
                String longitude= record.get("longitude");
                String sourceName="file_source";
                String info="file source";
                dataModels.add(new DataModel(caseId,insertDate,startDate,
                        endDate,latitude,longitude,sourceName,info));
            }
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller m = context.createUnmarshaller();
            Root root=(Root) m.unmarshal(xmlSource);
            root.dataModels.forEach(w->{
                w.setSourceName("xml");
                w.setInfo("xml file");
                dataModels.add(w);
            });
            return dataModels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

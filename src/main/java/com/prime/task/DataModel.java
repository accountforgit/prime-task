package com.prime.task;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataModel {
    @XmlElement(name = "CASEID")
    String caseId;
    @XmlElement(name = "LastUpdateDate")
    String insertDate;
    @XmlElement(name = "StartDate")
    String startDate;
    @XmlElement(name = "EndDate")
    String endDate;
    @XmlElement(name = "LATITUDE")
    String latitude;
    @XmlElement(name = "LONGITUDE")
    String longitude;
    String sourceName;
    String info;

    public DataModel(String caseId, String insertDate, String startDate, String endDate, String latitude, String longitude, String sourceName, String info) {
        this.caseId = caseId;
        this.insertDate = insertDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sourceName = sourceName;
        this.info = info;
    }
    public DataModel(){}

    public String getCaseId() {

        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

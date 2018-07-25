package com.prime.task;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "root")
public class Root {

    @XmlElement(name = "row")
    public List<DataModel> dataModels;



}

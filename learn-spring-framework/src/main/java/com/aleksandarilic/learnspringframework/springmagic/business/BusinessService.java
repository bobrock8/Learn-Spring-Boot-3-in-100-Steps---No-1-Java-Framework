package com.aleksandarilic.learnspringframework.springmagic.business;

import com.aleksandarilic.learnspringframework.springmagic.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {

    @Autowired
    DataService dataService;

    public long calculateSum() {
        var data = dataService.getData();
        return data.stream().reduce(Integer::sum).get();
    }
}

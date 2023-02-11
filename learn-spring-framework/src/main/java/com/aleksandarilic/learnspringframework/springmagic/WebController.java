package com.aleksandarilic.learnspringframework.springmagic;

import com.aleksandarilic.learnspringframework.springmagic.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebController {
    @Autowired
    BusinessService businessService;

    public long returnValueFromBusinessService() {
        return businessService.calculateSum();
    }
}

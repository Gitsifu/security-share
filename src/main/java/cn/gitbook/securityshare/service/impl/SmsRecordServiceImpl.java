package cn.gitbook.securityshare.service.impl;

import cn.gitbook.securityshare.entity.SmsRecord;
import cn.gitbook.securityshare.service.ISmsRecordService;
import org.springframework.stereotype.Service;

@Service
public class SmsRecordServiceImpl implements ISmsRecordService {
    @Override
    public SmsRecord getSmsRecordByPhone(String phone) {
        return SmsRecord.builder().code("123456").phone("13888888888").build();
    }
}

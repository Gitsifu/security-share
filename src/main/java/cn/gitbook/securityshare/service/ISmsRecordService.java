package cn.gitbook.securityshare.service;

import cn.gitbook.securityshare.entity.SmsRecord;

public interface ISmsRecordService {
    SmsRecord getSmsRecordByPhone(String phone);
}

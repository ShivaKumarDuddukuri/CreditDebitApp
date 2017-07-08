package com.tomojo.manager;

import com.tomojo.models.core.CreditInfo;
import com.tomojo.models.core.DebitInfo;
import com.tomojo.models.http.ControllerResponse;

public interface TransactionManager {

    ControllerResponse credit(CreditInfo creditInfo);

    ControllerResponse debit(DebitInfo debitInfo);

}
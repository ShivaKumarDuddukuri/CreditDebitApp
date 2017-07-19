package com.shiva.manager;

import com.shiva.models.core.CreditInfo;
import com.shiva.models.core.DebitInfo;
import com.shiva.models.http.ControllerResponse;

public interface TransactionManager {

    ControllerResponse credit(CreditInfo creditInfo);

    ControllerResponse debit(DebitInfo debitInfo);

}
package com.tomojo.services;

import com.tomojo.models.core.CreditInfo;
import com.tomojo.models.core.DebitInfo;
import com.tomojo.models.http.ControllerResponse;

public interface TransactionService {

    ControllerResponse credit(CreditInfo creditInfo);

    ControllerResponse debit(DebitInfo debitInfo);

}
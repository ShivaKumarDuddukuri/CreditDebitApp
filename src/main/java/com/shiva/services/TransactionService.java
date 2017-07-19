package com.shiva.services;

import com.shiva.models.core.CreditInfo;
import com.shiva.models.core.DebitInfo;
import com.shiva.models.http.ControllerResponse;

public interface TransactionService {

    ControllerResponse credit(CreditInfo creditInfo);

    ControllerResponse debit(DebitInfo debitInfo);

}
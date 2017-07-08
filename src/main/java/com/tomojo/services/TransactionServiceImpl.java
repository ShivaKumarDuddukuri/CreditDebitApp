package com.tomojo.services;

import com.tomojo.manager.TransactionManager;
import com.tomojo.models.core.CreditInfo;
import com.tomojo.models.core.DebitInfo;
import com.tomojo.models.http.ControllerResponse;
import com.tomojo.models.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory
            .getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionManager transactionManager;

    public ControllerResponse credit(CreditInfo creditInfo) {
        logger.info("Starting Crediting in Service layer");

        if (creditInfo == null) {
            return new ControllerResponse(HttpResponseStatus.INVALID_REQUEST, "Credit Information is not available . Please Check");
        }
        if (creditInfo.getCreditAmount() < 100) {
            return new ControllerResponse(HttpResponseStatus.INVALID_CREDIT_AMOUNT, " Credit Amount is less than 100 . Please Check");
        }
        if (creditInfo.getAccountNumber() < 10000) {
            return new ControllerResponse(HttpResponseStatus.INVALID_ACCOUNT_NUMBER, "Invalid account number is not valid . Please Check");
        }
        return transactionManager.credit(creditInfo);
    }

    public ControllerResponse debit(DebitInfo debitInfo) {
        logger.info("Starting Debiting in Service layer");
        if (debitInfo == null) {
            return new ControllerResponse(HttpResponseStatus.INVALID_REQUEST, "Debit Information is not available . Please Check");
        }
        if (debitInfo.getDebitAmount() < 100
                || debitInfo.getDebitAmount() > 10000) {
            return new ControllerResponse(HttpResponseStatus.INVALID_DEBIT_AMOUNT, "Debit Amount must be in between 100 to 10000 . Please Check");
        }
        if (debitInfo.getAccountNumber() < 10000) {
            return new ControllerResponse(HttpResponseStatus.INVALID_ACCOUNT_NUMBER, "Invalid account number is not valid . Please Check");
        }
        return transactionManager.debit(debitInfo);
    }
}
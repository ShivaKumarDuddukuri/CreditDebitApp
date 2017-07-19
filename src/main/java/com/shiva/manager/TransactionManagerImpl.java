package com.shiva.manager;

import com.shiva.dao.TransactionDao;
import com.shiva.models.core.CreditInfo;
import com.shiva.models.core.DebitInfo;
import com.shiva.models.http.ControllerResponse;
import com.shiva.models.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionManagerImpl implements TransactionManager {

    private static final Logger logger = LoggerFactory
            .getLogger(TransactionManagerImpl.class);

    @Autowired
    private TransactionDao transactionDao;

    public ControllerResponse credit(CreditInfo creditInfo) {
        logger.info("Starting Crediting in Manager layer");

        if (!transactionDao.isAccountPresent(creditInfo.getAccountNumber())) {
            return new ControllerResponse(HttpResponseStatus.INVALID_ACCOUNT_NUMBER, "Invalid AccountNumber . Please Check");
        }
        int accountBalance = transactionDao.getBalance(creditInfo.getAccountNumber());
        int updatedBalance = creditInfo.getCreditAmount() + accountBalance;
        if (transactionDao.credit(creditInfo.getAccountNumber(), updatedBalance)) {
            return new ControllerResponse(HttpResponseStatus.CREDIT_SUCCESS, " Credit Transaction Succeeded");
        } else {
            return new ControllerResponse(HttpResponseStatus.CREDIT_FAILED, " Credit Transaction  Failed ");
        }
    }

    public ControllerResponse debit(DebitInfo debitInfo) {
        logger.info("Starting Debiting in Manager layer");

        if (!transactionDao.isAccountPresent(debitInfo.getAccountNumber())) {
            return new ControllerResponse(HttpResponseStatus.INVALID_ACCOUNT_NUMBER, "Invalid AccountNumber . Please Check");
        }
        int accountBalance = transactionDao.getBalance(debitInfo.getAccountNumber());

        int updatedBalance = accountBalance - debitInfo.getDebitAmount();

        if (updatedBalance < 500) {
            return new ControllerResponse(HttpResponseStatus.DEBIT_FAILED, " Debit Transaction Failed due to Invalid Balance");
        }

        if (transactionDao.debit(debitInfo.getAccountNumber(),
                updatedBalance)) {
            return new ControllerResponse(HttpResponseStatus.DEBIT_SUCCESS, " Debit Transaction Succeeded ");
        } else {
            return new ControllerResponse(HttpResponseStatus.DEBIT_FAILED, " Debit Transaction Failed ");
        }
    }
}
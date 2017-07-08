package com.tomojo.controllers;

import com.tomojo.constants.TransactionURIConstants;
import com.tomojo.models.core.CreditInfo;
import com.tomojo.models.core.DebitInfo;
import com.tomojo.models.http.ControllerResponse;
import com.tomojo.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = TransactionURIConstants.TRANSACTION_BASE_URI)
public class TransactionController {

    private static final Logger logger = LoggerFactory
            .getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = TransactionURIConstants.CREDIT, method = RequestMethod.POST,
            produces = {"application/json"}, consumes = {"application/json"})
    public
    @ResponseBody
    ControllerResponse credit(@RequestBody CreditInfo creditInfo) {
        logger.info("Starting Debiting in Controller layer");
        return transactionService.credit(creditInfo);
    }

    @RequestMapping(value = TransactionURIConstants.DEBIT, method = RequestMethod.POST,
            produces = {"application/json"}, consumes = {"application/json"})
    public
    @ResponseBody
    ControllerResponse debit(@RequestBody DebitInfo debitInfo) {
        logger.info("Starting Crediting in Controller layer");
        return transactionService.debit(debitInfo);
    }

}

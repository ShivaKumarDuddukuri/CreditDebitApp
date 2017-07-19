package com.shiva.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class TransactionDaoImpl implements TransactionDao {

    private static final Logger logger = LoggerFactory
            .getLogger(TransactionDaoImpl.class);

    private JdbcTemplate jdbcTemplate;

    public TransactionDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public boolean credit(int accountNumber, int creditAmount) {
        logger.info("Starting Crediting in Dao layer");

        String CREDIT = " update Transactions set amount =" + creditAmount
                + " where accountNumber =" + accountNumber;
        int rows = 0;
        try {
            rows = jdbcTemplate.update(CREDIT);
        } catch (DataAccessException dae) {
            logger.error("Error while Crediting an Account  " + dae.getMessage());
        }
        return rows >= 1;
    }

    @Transactional
    public boolean debit(int accountNumber, int debitAmount) {
        logger.info("Starting Debiting in Dao layer");
        String DEBIT = " update Transactions set amount =" + debitAmount
                + " where accountNumber =" + accountNumber;
        int rows = 0;
        try {
            rows = jdbcTemplate.update(DEBIT);
        } catch (DataAccessException dae) {
            logger.error("Error while Debiting an Account  " + dae.getMessage());
        }
        return rows >= 1;
    }

    public boolean isAccountPresent(int accountNumber) {
        logger.info("Starting Account Existence Verification in Dao layer");
        String ACCOUNT_EXISTENCE = " select count(*) from Transactions  where accountNumber =" + accountNumber;
        int rows = 0;
        try {
            rows = jdbcTemplate.queryForObject(ACCOUNT_EXISTENCE, Integer.class);
        } catch (DataAccessException dae) {
            logger.error("Error while checking for Account Existense " + dae.getMessage());
        }
        return rows >= 1;
    }

    public int getBalance(int accountNumber) {
        logger.info("Starting Balance Enquiry in Dao layer");
        String BALANCE_ENQUIRY = " Select amount from Transactions where accountNumber =" + accountNumber;
        int balance = 0;
        try {
            balance = jdbcTemplate.queryForObject(BALANCE_ENQUIRY, Integer.class);
        } catch (DataAccessException dae) {
            logger.error("Error while retrieving balance " + dae.getMessage());
        }
        return balance;
    }
}
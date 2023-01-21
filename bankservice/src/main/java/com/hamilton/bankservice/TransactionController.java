package com.hamilton.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TransactionController {

//    private static class TransactionForm{
//
//        public TransactionForm(){}
//        public TransactionForm( ){}
//    }

    @Autowired
    private TransactionDAO transaction;

    @Autowired
    private AccountDAO account;


    @ResponseBody
    @PostMapping(value="/transaction")
    public void newTransaction(@RequestBody Transaction tf){
                if (tf.getType().equals("1")){
                    transaction.withdrawTransaction(tf.getfromAccount(),tf.getamount());
                }
                else if (tf.getType().equals("2")){
                    transaction.depositTransaction(tf.gettoAccount(), tf.getamount());
                }
                else if (tf.getType().equals("0")){
                    transaction.transferTransaction(tf.getfromAccount(),tf.gettoAccount(), tf.getfromSortCode(), tf.gettoAccountSortCode(), tf.getamount());
                    System.out.println(tf.gettoAccountSortCode());
                }}

}




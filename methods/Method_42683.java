/** 
 * ?????? 
 */
@Transactional(rollbackFor=Exception.class) public String buildAccountNo(){
  String accountNoSeq=this.getSeqNextValue("ACCOUNT_NO_SEQ");
  String dateString=DateUtils.toString(new Date(),"yyyyMMdd");
  String accountNo=ACCOUNT_NO_PREFIX + dateString + accountNoSeq.substring(accountNoSeq.length() - 8,accountNoSeq.length());
  return accountNo;
}

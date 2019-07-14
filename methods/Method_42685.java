/** 
 * ???????
 */
@Override public String buildBankOrderNo(){
  String bankOrderNoSeq=this.getSeqNextValue("BANK_ORDER_NO_SEQ");
  String dateString=DateUtils.toString(new Date(),"yyyyMMdd");
  String bankOrderNo=BANK_ORDER_NO_PREFIX + dateString + bankOrderNoSeq.substring(bankOrderNoSeq.length() - 8,bankOrderNoSeq.length());
  return bankOrderNo;
}

/** 
 * ???????
 */
@Override public String buildTrxNo(){
  String trxNoSeq=this.getSeqNextValue("TRX_NO_SEQ");
  String dateString=DateUtils.toString(new Date(),"yyyyMMdd");
  String trxNo=TRX_NO_PREFIX + dateString + trxNoSeq.substring(trxNoSeq.length() - 8,trxNoSeq.length());
  return trxNo;
}

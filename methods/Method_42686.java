/** 
 * ??????? 
 */
public String buildReconciliationNo(){
  String batchNoSeq=this.getSeqNextValue("RECONCILIATION_BATCH_NO_SEQ");
  String dateString=DateUtils.toString(new Date(),"yyyyMMdd");
  String batchNo=RECONCILIATION_BATCH_NO + dateString + batchNoSeq.substring(batchNoSeq.length() - 8,batchNoSeq.length());
  return batchNo;
}

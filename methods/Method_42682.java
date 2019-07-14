/** 
 * ?????? 
 */
@Transactional(rollbackFor=Exception.class) public String buildUserNo(){
  String userNoSeq=this.getSeqNextValue("USER_NO_SEQ");
  String dateString=DateUtils.toString(new Date(),"yyyyMMdd");
  String userNo=USER_NO_PREFIX + dateString + userNoSeq.substring(userNoSeq.length() - 8,userNoSeq.length());
  return userNo;
}

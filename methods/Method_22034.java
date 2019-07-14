/** 
 * ??????????.
 */
public void clearAllCompletedInfo(){
  jobNodeStorage.removeJobNodeIfExisted(GuaranteeNode.COMPLETED_ROOT);
}

/** 
 * Point transfer.
 * @param context the specified context
 */
@RequestProcessing(value="/point/transfer",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class,PointTransferValidation.class}) public void pointTransfer(final RequestContext context){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  context.renderJSON(ret);
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=(JSONObject)context.attr(Keys.REQUEST);
  final int amount=requestJSONObject.optInt(Common.AMOUNT);
  final JSONObject toUser=(JSONObject)context.attr(Common.TO_USER);
  final JSONObject currentUser=Sessions.getUser();
  String memo=(String)context.attr(Pointtransfer.MEMO);
  if (StringUtils.isBlank(memo)) {
    memo="";
  }
  final String fromId=currentUser.optString(Keys.OBJECT_ID);
  final String toId=toUser.optString(Keys.OBJECT_ID);
  final String transferId=pointtransferMgmtService.transfer(fromId,toId,Pointtransfer.TRANSFER_TYPE_C_ACCOUNT2ACCOUNT,amount,toId,System.currentTimeMillis(),memo);
  final boolean succ=null != transferId;
  ret.put(Keys.STATUS_CODE,succ);
  if (!succ) {
    ret.put(Keys.MSG,langPropsService.get("transferFailLabel"));
  }
 else {
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,toId);
    notification.put(Notification.NOTIFICATION_DATA_ID,transferId);
    notificationMgmtService.addPointTransferNotification(notification);
  }
}

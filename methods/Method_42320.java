/** 
 * ??????????.<br/>
 * @param notifyId ????ID.<br/>
 * @param merchantNo ????.<br/>
 * @param merchantOrderNo ?????.<br/>
 * @param request ????.<br/>
 * @param response ????.<br/>
 * @param httpStatus ????(HTTP??).<br/>
 * @return ????
 */
public long saveNotifyRecordLogs(String notifyId,String merchantNo,String merchantOrderNo,String request,String response,int httpStatus){
  RpNotifyRecordLog notifyRecordLog=new RpNotifyRecordLog();
  notifyRecordLog.setHttpStatus(httpStatus);
  notifyRecordLog.setMerchantNo(merchantNo);
  notifyRecordLog.setMerchantOrderNo(merchantOrderNo);
  notifyRecordLog.setNotifyId(notifyId);
  notifyRecordLog.setRequest(request);
  notifyRecordLog.setResponse(response);
  notifyRecordLog.setCreateTime(new Date());
  notifyRecordLog.setEditTime(new Date());
  return rpNotifyService.createNotifyRecordLog(notifyRecordLog);
}

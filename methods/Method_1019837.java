/** 
 * Print Common Profile Log
 * @param profileApp     profileApp
 * @param protocolType   protocolType
 * @param profileMessage profileMessage
 */
public void profile(String profileApp,String protocolType,String profileMessage){
  Map<String,Object> tags=new HashMap<String,Object>();
  tags.putAll(this.getTagsWithStr());
  tags.putAll(this.getTagsWithBool());
  tags.putAll(this.getTagsWithNumber());
  tags.put(SpanTags.CURR_APP_TAG.getKey(),profileApp);
  CommonLogSpan commonLogSpan=new CommonLogSpan(this.sofaTracer,System.currentTimeMillis(),this.getOperationName(),this.getSofaTracerSpanContext(),tags);
  commonLogSpan.addSlot(protocolType);
  commonLogSpan.addSlot(profileMessage);
  CommonTracerManager.reportProfile(commonLogSpan);
}

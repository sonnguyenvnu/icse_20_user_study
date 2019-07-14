/** 
 * key??????????
 * @param attachments the attachments
 * @return context attachments
 */
public RpcInternalContext setAttachments(Map<String,Object> attachments){
  if (attachments != null && attachments.size() > 0) {
    for (    Map.Entry<String,Object> entry : attachments.entrySet()) {
      setAttachment(entry.getKey(),entry.getValue());
    }
  }
  return this;
}

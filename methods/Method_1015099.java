@Override public String digest(Message message){
  return "??????(" + (orignalPayload != null ? orignalPayload.contentType : "") + ")";
}

public void attach(String key,Object data){
  if (attachments == null) {
    attachments=new HashMap<>();
  }
  attachments.put(key,data);
}

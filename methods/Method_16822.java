public final State doExec(){
  String filedName=(String)this.conf.get("fieldName");
  State state;
  if ("true".equals(this.conf.get("isBase64"))) {
    state=Base64Uploader.save(this.request.getParameter(filedName),this.conf);
  }
 else {
    state=BinaryUploader.save(this.request,this.conf);
  }
  return state;
}

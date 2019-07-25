public void init(InputStream ins) throws Exception {
  if (ins != null)   props.load(ins);
  if (props.containsKey("log.adapter")) {
    LogAdapter la=(LogAdapter)Class.forName(props.getProperty("log.adapter")).newInstance();
    Logs.setAdapter(la);
  }
  inputEnc=props.getProperty("enc.input");
  outputEnc=props.getProperty("enc.output");
  methodParam=props.getProperty("http.hidden_method_param");
  allowHTTPMethodOverride="true".equals(props.getProperty("http.method_override"));
  uploadEnable="true".equals(props.getProperty("upload.enable"));
  if (uploadEnable) {
    String tmpPath=props.getProperty("upload.tmpdir",System.getProperty("java.io.tmpdir") + "/whale");
    UU32FilePool fp=new UU32FilePool(tmpPath);
    UploadingContext uc=new UploadingContext(fp);
    Mirror<UploadingContext> mirror=Mirror.me(uc);
    for (    Object _key : props.keySet()) {
      String key=_key.toString();
      if (!key.startsWith("upload.")) {
        continue;
      }
      key=key.substring("upload.".length());
      if ("tmpdir".equals(key) || "exclusions".equals(key) || "enable".equals(key)) {
        continue;
      }
      mirror.setValue(uc,key,props.get(_key));
    }
    this.uc=uc;
  }
}

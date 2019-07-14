private void initEnv() throws IOException {
  try {
    this.jsonConfig=JSON.parseObject(FileUtils.reader2String(configFileName));
  }
 catch (  Exception e) {
    log.warn("read ueditor config file error",e);
    this.jsonConfig=null;
  }
}

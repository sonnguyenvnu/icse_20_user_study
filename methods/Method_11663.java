@Override public void process(Object o,Task task){
  String path=this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
  try {
    String filename;
    if (o instanceof HasKey) {
      filename=path + ((HasKey)o).key() + ".json";
    }
 else {
      filename=path + DigestUtils.md5Hex(ToStringBuilder.reflectionToString(o)) + ".json";
    }
    PrintWriter printWriter=new PrintWriter(new FileWriter(getFile(filename)));
    printWriter.write(JSON.toJSONString(o));
    printWriter.close();
  }
 catch (  IOException e) {
    logger.warn("write file error",e);
  }
}

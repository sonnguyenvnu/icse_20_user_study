@Override public void process(ResultItems resultItems,Task task){
  String path=this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
  try {
    PrintWriter printWriter=new PrintWriter(new FileWriter(getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".json")));
    printWriter.write(JSON.toJSONString(resultItems.getAll()));
    printWriter.close();
  }
 catch (  IOException e) {
    logger.warn("write file error",e);
  }
}

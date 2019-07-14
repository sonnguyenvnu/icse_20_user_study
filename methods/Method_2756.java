@Override public boolean saveTxtTo(String path){
  try {
    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path)));
    bw.write(toString());
    bw.close();
  }
 catch (  Exception e) {
    logger.warning("??????????" + path + "?????" + e);
    return false;
  }
  return true;
}

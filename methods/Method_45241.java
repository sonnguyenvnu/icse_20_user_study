@Override public void write(final String content){
  try {
    Files.append(content,file,charset);
  }
 catch (  Exception e) {
    throw new MocoException(e);
  }
}

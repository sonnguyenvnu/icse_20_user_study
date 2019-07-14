@Override protected byte[] doReadFor(final Request request){
  File file=new File(targetFileName(request));
  if (!file.exists()) {
    throw new IllegalArgumentException(format("%s does not exist",file.getPath()));
  }
  try {
    return toByteArray(file);
  }
 catch (  IOException e) {
    throw new MocoException(e);
  }
}

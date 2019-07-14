@Override public boolean canServeUri(String uri,File rootDir){
  File f=new File(rootDir,uri);
  return f.exists();
}

public File getCacheDir(String name){
  File dir=new File(new File(s_dataDir,"cache"),name);
  dir.mkdirs();
  return dir;
}

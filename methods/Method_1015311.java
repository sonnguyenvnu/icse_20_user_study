public List<String> load(File dir,String uin){
  FileStorage fs=get(dir,uin);
  return fs.getLast(max);
}

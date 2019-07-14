public ArrayList<String> getParentsFolders(){
  ArrayList<String> result=new ArrayList<>();
  File f=new File(getPath());
  while (f != null && f.canRead()) {
    result.add(f.getPath());
    f=f.getParentFile();
  }
  return result;
}

static private Collection<? extends File> scan(File f){
  List<File> ret=new ArrayList<>();
  File[] files=f.listFiles(new FileFilter(){
    @Override public boolean accept(    File pathname){
      if (pathname.getName().toLowerCase().endsWith(".gpkg"))       return true;
      return false;
    }
  }
);
  if (files != null) {
    Collections.addAll(ret,files);
  }
  return ret;
}

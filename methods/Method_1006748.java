public static File[] local(File dir,final String regex){
  return dir.listFiles(new FilenameFilter(){
    @Override public boolean accept(    File dir,    String name){
      return pattern.matcher(new File(name).getName()).matches();
    }
  }
);
}

@Override public boolean accept(API api,File file){
  return file.exists() && file.isFile() && file.canRead() && file.getName().toLowerCase().endsWith(".ear");
}

public boolean trim(File file){
  return trim(file.getPath(),file.isDirectory());
}

boolean accepts(String path){
  return !(MPSFileTypesManager.isFileIgnored(path));
}

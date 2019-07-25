public static Set<File> convert(Set<FileWithSuffix> all){
  final Set<File> result=new HashSet<File>();
  for (  FileWithSuffix f : all) {
    result.add(f.file.getUnderlyingFile());
  }
  return result;
}

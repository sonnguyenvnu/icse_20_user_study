private static void addSourcesFilesToCPD(List<File> files,CPD cpd,boolean recursive){
  try {
    for (    File file : files) {
      if (!file.exists()) {
        throw new FileNotFoundException("Couldn't find directory/file '" + file + "'");
      }
 else       if (file.isDirectory()) {
        if (recursive) {
          cpd.addRecursively(file);
        }
 else {
          cpd.addAllInDirectory(file);
        }
      }
 else {
        cpd.add(file);
      }
    }
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
}

public void extractFiles(String[] files) throws IOException {
  HashSet<String> filesToExtract=new HashSet<>(files.length);
  Collections.addAll(filesToExtract,files);
  extractWithFilter((relativePath,isDir) -> {
    if (filesToExtract.contains(relativePath)) {
      if (!isDir)       filesToExtract.remove(relativePath);
      return true;
    }
 else {
      for (      String path : filesToExtract) {
        if (relativePath.startsWith(path) || relativePath.startsWith("/" + path)) {
          return true;
        }
      }
      return false;
    }
  }
);
}

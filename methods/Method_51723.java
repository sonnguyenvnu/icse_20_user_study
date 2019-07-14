private List<Path> listMdFiles(Path pagesDirectory){
  try {
    return Files.walk(pagesDirectory).filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".md")).collect(Collectors.toList());
  }
 catch (  IOException ex) {
    throw new RuntimeException("error listing files in " + pagesDirectory,ex);
  }
}

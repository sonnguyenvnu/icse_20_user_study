private String getRuleClassSourceFilepath(String ruleClass) throws IOException {
  final String relativeSourceFilename=ruleClass.replaceAll("\\.",Matcher.quoteReplacement(File.separator)) + ".java";
  final List<Path> foundPathResult=new LinkedList<>();
  Files.walkFileTree(root,new SimpleFileVisitor<Path>(){
    @Override public FileVisitResult visitFile(    Path file,    BasicFileAttributes attrs) throws IOException {
      String path=file.toString();
      if (path.contains("src") && path.endsWith(relativeSourceFilename)) {
        foundPathResult.add(file);
        return FileVisitResult.TERMINATE;
      }
      return super.visitFile(file,attrs);
    }
  }
);
  if (!foundPathResult.isEmpty()) {
    Path foundPath=foundPathResult.get(0);
    foundPath=root.relativize(foundPath);
    return FilenameUtils.normalize(foundPath.toString(),true);
  }
  return FilenameUtils.normalize(relativeSourceFilename,true);
}

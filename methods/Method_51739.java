/** 
 * Searches for the source file of the given ruleset. This provides the information for the "editme" link.
 * @param ruleset the ruleset to search for.
 * @return
 * @throws IOException
 */
private String getRuleSetSourceFilepath(RuleSet ruleset) throws IOException {
  final String rulesetFilename=FilenameUtils.normalize(StringUtils.chomp(ruleset.getFileName()));
  final List<Path> foundPathResult=new LinkedList<>();
  Files.walkFileTree(root,new SimpleFileVisitor<Path>(){
    @Override public FileVisitResult visitFile(    Path file,    BasicFileAttributes attrs) throws IOException {
      String path=file.toString();
      if (path.contains("src") && path.endsWith(rulesetFilename)) {
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
  return StringUtils.chomp(ruleset.getFileName());
}

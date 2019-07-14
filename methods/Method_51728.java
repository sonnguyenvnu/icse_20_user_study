public static List<String> findAdditionalRulesets(Path basePath){
  try {
    List<String> additionalRulesets=new ArrayList<>();
    Pattern rulesetPattern=Pattern.compile("^.+" + Pattern.quote(File.separator) + "pmd-\\w+" + Pattern.quote(FilenameUtils.normalize("/src/main/resources/rulesets/")) + "\\w+" + Pattern.quote(File.separator) + "\\w+.xml$");
    Files.walkFileTree(basePath,new SimpleFileVisitor<Path>(){
      @Override public FileVisitResult visitFile(      Path file,      BasicFileAttributes attrs) throws IOException {
        if (rulesetPattern.matcher(file.toString()).matches()) {
          additionalRulesets.add(file.toString());
        }
        return FileVisitResult.CONTINUE;
      }
    }
);
    return additionalRulesets;
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}

@Override protected void process(List<File> files,Formatter formatter) throws MojoExecutionException {
  if (skip) {
    getLog().info("Spotless check skipped");
    return;
  }
  List<File> problemFiles=new ArrayList<>();
  for (  File file : files) {
    try {
      if (!formatter.isClean(file)) {
        problemFiles.add(file);
      }
    }
 catch (    IOException e) {
      throw new MojoExecutionException("Unable to format file " + file,e);
    }
  }
  if (!problemFiles.isEmpty()) {
    throw new MojoExecutionException(DiffMessageFormatter.builder().runToFix("Run 'mvn spotless:apply' to fix these violations.").isPaddedCell(false).formatter(formatter).problemFiles(problemFiles).getMessage());
  }
}

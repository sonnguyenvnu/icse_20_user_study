@Override protected void process(List<File> files,Formatter formatter) throws MojoExecutionException {
  if (skip) {
    getLog().info("Spotless apply skipped");
    return;
  }
  for (  File file : files) {
    try {
      formatter.applyTo(file);
    }
 catch (    IOException e) {
      throw new MojoExecutionException("Unable to format file " + file,e);
    }
  }
}

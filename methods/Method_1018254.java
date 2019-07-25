public int transform() throws IOException {
  final Transformer transformer=new Transformer();
  transformer.setErrorListener(System.err::println);
  boolean error=false;
  int count=0;
  final Path outputDir=getOutputDirectory();
  for (  Path path : fileList) {
    byte[] bytes=null;
    try (InputStream in=new FileInputStream(path.toFile())){
      bytes=transformer.instrument(classLoader,in);
    }
 catch (    Exception e) {
      error("Error instrumenting " + path,e);
      error=true;
    }
    if (bytes != null) {
      if (verbose) {
        info("instrumented: " + path);
      }
      if (outputDir != null) {
        final Path outPath=outputDir.resolve(new ClassReader(bytes).getClassName() + ".class");
        final Path outParent=outPath.getParent();
        if (!Files.exists(outParent)) {
          Files.createDirectories(outParent);
        }
        Files.write(outPath,bytes);
      }
 else {
        Files.write(path,bytes);
      }
      count++;
    }
  }
  return error ? -1 : count;
}

static void generate(Location location,String fileName,String content,ProcessingEnvironment processingEnv,Element... elements) throws IOException {
  FileObject resource=processingEnv.getFiler().createResource(location,"",fileName,elements);
  write(content,resource);
}

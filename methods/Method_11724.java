public ScriptProcessorBuilder scriptFromClassPathFile(String fileName){
  try {
    InputStream resourceAsStream=ScriptProcessor.class.getClassLoader().getResourceAsStream(fileName);
    this.script=IOUtils.toString(resourceAsStream);
  }
 catch (  IOException e) {
    throw new IllegalArgumentException(e);
  }
  return this;
}

@TaskAction public void translate(){
  getProject().getLogger().info("Translating data schemas ...");
  _destinationDir.mkdirs();
  String resolverPathStr=_resolverPath.plus(getProject().files(_inputDir)).getAsPath();
  getProject().javaexec(javaExecSpec -> {
    javaExecSpec.setMain("com.linkedin.restli.tools.data.SchemaFormatTranslator");
    javaExecSpec.setClasspath(_codegenClasspath);
    javaExecSpec.jvmArgs("-Dgenerator.resolver.path=" + resolverPathStr);
    javaExecSpec.args("--source-format");
    javaExecSpec.args(_sourceFormat.getFileExtension());
    javaExecSpec.args("--destination-format");
    javaExecSpec.args(_destinationFormat.getFileExtension());
    javaExecSpec.args(resolverPathStr);
    javaExecSpec.args(_inputDir.getAbsolutePath());
    javaExecSpec.args(_destinationDir.getAbsolutePath());
  }
);
}

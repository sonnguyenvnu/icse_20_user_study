private void writeJavaFile() throws IOException {
  String header=Resources.toString(Resources.getResource("license.txt"),UTF_8).trim();
  JavaFile.builder(getClass().getPackage().getName(),factory.build()).addFileComment(header,Year.now(timeZone)).indent("  ").build().writeTo(directory);
  for (  TypeSpec typeSpec : factoryTypes) {
    JavaFile.builder(getClass().getPackage().getName(),typeSpec).addFileComment(header,Year.now(timeZone)).indent("  ").build().writeTo(directory);
  }
}

private String fileToString(Path mdFile){
  try (InputStream inputStream=Files.newInputStream(mdFile)){
    return IOUtils.toString(inputStream,Charset.forName("UTF-8"));
  }
 catch (  IOException ex) {
    throw new RuntimeException("error reading " + mdFile,ex);
  }
}

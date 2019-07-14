private void writeBinaryFileAndTranslateExceptions(byte[] contents,File toFile){
  try {
    Files.write(contents,toFile);
  }
 catch (  IOException ioe) {
    throw new RuntimeException(ioe);
  }
}

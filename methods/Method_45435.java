private static FileInputStream toInputStream(final File file){
  try {
    return new FileInputStream(file);
  }
 catch (  FileNotFoundException e) {
    throw new MocoException(e);
  }
}

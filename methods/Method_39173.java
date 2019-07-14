private static FileInputStream createFileInputStream(final File file){
  try {
    return new FileInputStream(file);
  }
 catch (  FileNotFoundException fis) {
    throw new MadvocException(fis);
  }
}

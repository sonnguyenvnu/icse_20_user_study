/** 
 * @nowebref
 */
static public InputStream createInput(File file){
  if (file == null) {
    throw new IllegalArgumentException("File passed to createInput() was null");
  }
  if (!file.exists()) {
    System.err.println(file + " does not exist, createInput() will return null");
    return null;
  }
  try {
    InputStream input=new FileInputStream(file);
    final String lower=file.getName().toLowerCase();
    if (lower.endsWith(".gz") || lower.endsWith(".svgz")) {
      return new BufferedInputStream(new GZIPInputStream(input));
    }
    return new BufferedInputStream(input);
  }
 catch (  IOException e) {
    System.err.println("Could not createInput() for " + file);
    e.printStackTrace();
    return null;
  }
}

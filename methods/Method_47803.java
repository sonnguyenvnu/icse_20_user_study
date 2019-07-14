@NonNull private InputStream open(String fname) throws IOException {
  InputStream resource=getClass().getResourceAsStream(fname);
  if (resource != null)   return resource;
  File file=new File("uhabits-core/src/main/resources/" + fname);
  if (file.exists())   return new FileInputStream(file);
  throw new RuntimeException("resource not found: " + fname);
}

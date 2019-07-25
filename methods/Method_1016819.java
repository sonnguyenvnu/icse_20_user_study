public CharSequence pipe(File file) throws java.io.FileNotFoundException, java.io.IOException {
  BufferedReader br=null;
  if (encoding == null) {
    br=Files.newBufferedReader(file.toPath(),Charset.defaultCharset());
  }
 else {
    br=new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
  }
  CharSequence cs=pipe(br);
  br.close();
  return cs;
}

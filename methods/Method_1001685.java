public List<FileImageData> create() throws IOException {
  dir.mkdirs();
  if (dir.exists() == false) {
    throw new IOException("Cannot create " + dir);
  }
  final File f=new File(dir,"index.html");
  final PrintWriter pw=new PrintWriter(f);
  pw.println("<html>");
  printAllType(pw,LeafType.ENUM);
  printAllType(pw,LeafType.INTERFACE);
  printAllType(pw,LeafType.ANNOTATION);
  printAllType(pw,LeafType.ABSTRACT_CLASS);
  printAllType(pw,LeafType.CLASS);
  htmlClose(pw);
  return Arrays.asList(new FileImageData(dir,null));
}

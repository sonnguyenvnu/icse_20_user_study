static void writeUTF(OutputStream output,String... lines) throws IOException {
  if (utf8 == null) {
    utf8=Charset.forName("UTF-8");
  }
  for (  String str : lines) {
    output.write(str.getBytes(utf8));
    output.write('\n');
  }
}

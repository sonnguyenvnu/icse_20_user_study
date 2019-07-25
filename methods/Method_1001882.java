private void out(OutputStream os,String s) throws IOException {
  os.write(s.getBytes("UTF-8"));
  os.write("\n".getBytes("UTF-8"));
}

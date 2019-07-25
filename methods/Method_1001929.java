private void out(OutputStream os,String s) throws IOException {
  os.write(s.getBytes());
  os.write("\n".getBytes());
}

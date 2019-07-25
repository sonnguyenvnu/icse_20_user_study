public void save(OutputStream out) throws IOException {
  out.write(getCurrentBuffer().toString().getBytes());
}

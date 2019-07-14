private void writeBaseProperty(final BufferedWriter bw,final String key,final String value) throws IOException {
  bw.write(key + '=' + value);
  bw.newLine();
}

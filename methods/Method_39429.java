private void writeProfileProperty(final BufferedWriter bw,final String profileName,final String key,final String value) throws IOException {
  bw.write(key + '<' + profileName + '>' + '=' + value);
  bw.newLine();
}

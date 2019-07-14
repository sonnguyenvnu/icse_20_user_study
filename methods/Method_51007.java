private void writeUntilEOF() throws IOException {
  String line;
  while ((line=reader.readLine()) != null) {
    writer.write(line);
  }
}

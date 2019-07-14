private LineNumberReader getLineReader() throws IOException {
  return new LineNumberReader(Files.newBufferedReader(getProperty(EXCEPTION_FILE_DESCRIPTOR).toPath(),StandardCharsets.UTF_8));
}

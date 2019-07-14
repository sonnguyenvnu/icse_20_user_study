public static boolean isID3v1StartPosition(InputStream input) throws IOException {
  input.mark(3);
  try {
    return input.read() == 'T' && input.read() == 'A' && input.read() == 'G';
  }
  finally {
    input.reset();
  }
}

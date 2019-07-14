public static boolean isID3v2StartPosition(InputStream input) throws IOException {
  input.mark(3);
  try {
    return input.read() == 'I' && input.read() == 'D' && input.read() == '3';
  }
  finally {
    input.reset();
  }
}

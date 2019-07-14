public String readString(String enc) throws IOException {
  return readString((int)getRemaining(),enc);
}

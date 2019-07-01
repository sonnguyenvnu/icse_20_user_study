private byte[] _XXXXX_(final int code) throws IOException {
  if ((code >= codes) || (code < 0)) {
    throw new IOException("Bad Code: " + code + " codes: "+ codes+ " code_size: "+ codeSize+ ", table: "+ table.length);
  }
  return table[code];
}
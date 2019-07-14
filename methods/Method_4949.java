private static void writeUnsignedInt(DataOutputStream outputStream,long value) throws IOException {
  outputStream.writeByte((int)(value >>> 24) & 0xFF);
  outputStream.writeByte((int)(value >>> 16) & 0xFF);
  outputStream.writeByte((int)(value >>> 8) & 0xFF);
  outputStream.writeByte((int)value & 0xFF);
}

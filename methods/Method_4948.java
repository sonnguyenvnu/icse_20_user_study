private static void writeNullTerminatedString(DataOutputStream dataOutputStream,String value) throws IOException {
  dataOutputStream.writeBytes(value);
  dataOutputStream.writeByte(0);
}

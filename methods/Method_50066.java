/** 
 * Writes Netscape application extension to define repeat count.
 */
private void writeNetscapeExt() throws IOException {
  out.write(0x21);
  out.write(0xff);
  out.write(11);
  writeString("NETSCAPE" + "2.0");
  out.write(3);
  out.write(1);
  writeShort(repeat);
  out.write(0);
}

/** 
 * OutputStream.write(int) ? write ??? byte, ????? int
 * @param out OutputStream
 * @param i int value
 * @throws IOException if an I/O error occurs.
 */
private void writeInt(OutputStream out,int i) throws IOException {
  out.write((byte)(i >> 24));
  out.write((byte)(i >> 16));
  out.write((byte)(i >> 8));
  out.write(i);
}

/** 
 * Writes 16-bit fixed-point number divided as 8.8.
 * @param f   an <code>int</code> to be written.
 * @exception IOException  if an I/O error occurs.
 * @see java.io.FilterOutputStream#out
 */
public void writeFixed8D8(float f) throws IOException {
  float v=(f >= 0) ? f : -f;
  int wholePart=(int)v;
  int fractionPart=(int)((v - wholePart) * 256);
  int t=(wholePart << 8) + fractionPart;
  if (f < 0) {
    t=t - 1;
  }
  writeUShort(t);
}

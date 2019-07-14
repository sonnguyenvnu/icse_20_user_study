/** 
 * Writes 32-bit fixed-point number divided as 16.16.
 * @param f   an <code>int</code> to be written.
 * @exception IOException  if an I/O error occurs.
 * @see java.io.FilterOutputStream#out
 */
public void writeFixed16D16(double f) throws IOException {
  double v=(f >= 0) ? f : -f;
  int wholePart=(int)Math.floor(v);
  int fractionPart=(int)((v - wholePart) * 65536);
  int t=(wholePart << 16) + fractionPart;
  if (f < 0) {
    t=t - 1;
  }
  writeInt(t);
}

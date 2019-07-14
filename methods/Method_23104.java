/** 
 * Writes 32-bit fixed-point number divided as 2.30.
 * @param f   an <code>int</code> to be written.
 * @exception IOException  if an I/O error occurs.
 * @see java.io.FilterOutputStream#out
 */
public void writeFixed2D30(double f) throws IOException {
  double v=(f >= 0) ? f : -f;
  int wholePart=(int)v;
  int fractionPart=(int)((v - wholePart) * 1073741824);
  int t=(wholePart << 30) + fractionPart;
  if (f < 0) {
    t=t - 1;
  }
  writeInt(t);
}

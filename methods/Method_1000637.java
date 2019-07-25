/** 
 * ?????????????
 */
public static boolean equals(InputStream sA,InputStream sB) throws IOException {
  int dA;
  while ((dA=sA.read()) != -1) {
    int dB=sB.read();
    if (dA != dB)     return false;
  }
  return sB.read() == -1;
}

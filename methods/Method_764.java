/** 
 * Decodes a BASE64 encoded string that is known to be resonably well formatted. The method is about twice as fast as decode(String). The preconditions are:<br> + The array must have a line length of 76 chars OR no line separators at all (one line).<br> + Line separator must be "\r\n", as specified in RFC 2045 + The array must not contain illegal characters within the encoded string<br> + The array CAN have illegal characters at the beginning and end, those will be dealt with appropriately.<br>
 * @author Mikael Grev Date: 2004-aug-02 Time: 11:31:11
 * @param s The source string. Length 0 will return an empty array. <code>null</code> will throw an exception.
 * @return The decoded array of bytes. May be of length 0.
 */
public static byte[] decodeBase64(String s){
  int sLen=s.length();
  if (sLen == 0) {
    return new byte[0];
  }
  int sIx=0, eIx=sLen - 1;
  while (sIx < eIx && IA[s.charAt(sIx) & 0xff] < 0)   sIx++;
  while (eIx > 0 && IA[s.charAt(eIx) & 0xff] < 0)   eIx--;
  int pad=s.charAt(eIx) == '=' ? (s.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
  int cCnt=eIx - sIx + 1;
  int sepCnt=sLen > 76 ? (s.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
  int len=((cCnt - sepCnt) * 6 >> 3) - pad;
  byte[] dArr=new byte[len];
  int d=0;
  for (int cc=0, eLen=(len / 3) * 3; d < eLen; ) {
    int i=IA[s.charAt(sIx++)] << 18 | IA[s.charAt(sIx++)] << 12 | IA[s.charAt(sIx++)] << 6 | IA[s.charAt(sIx++)];
    dArr[d++]=(byte)(i >> 16);
    dArr[d++]=(byte)(i >> 8);
    dArr[d++]=(byte)i;
    if (sepCnt > 0 && ++cc == 19) {
      sIx+=2;
      cc=0;
    }
  }
  if (d < len) {
    int i=0;
    for (int j=0; sIx <= eIx - pad; j++)     i|=IA[s.charAt(sIx++)] << (18 - j * 6);
    for (int r=16; d < len; r-=8)     dArr[d++]=(byte)(i >> r);
  }
  return dArr;
}

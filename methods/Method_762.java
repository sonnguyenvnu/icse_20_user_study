/** 
 * Decodes a BASE64 encoded char array that is known to be resonably well formatted. The method is about twice as fast as #decode(char[]). The preconditions are:<br> + The array must have a line length of 76 chars OR no line separators at all (one line).<br> + Line separator must be "\r\n", as specified in RFC 2045 + The array must not contain illegal characters within the encoded string<br> + The array CAN have illegal characters at the beginning and end, those will be dealt with appropriately.<br>
 * @author Mikael Grev Date: 2004-aug-02 Time: 11:31:11
 * @param chars The source array. Length 0 will return an empty array. <code>null</code> will throw an exception.
 * @return The decoded array of bytes. May be of length 0.
 */
public static byte[] decodeBase64(char[] chars,int offset,int charsLen){
  if (charsLen == 0) {
    return new byte[0];
  }
  int sIx=offset, eIx=offset + charsLen - 1;
  while (sIx < eIx && IA[chars[sIx]] < 0)   sIx++;
  while (eIx > 0 && IA[chars[eIx]] < 0)   eIx--;
  int pad=chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0;
  int cCnt=eIx - sIx + 1;
  int sepCnt=charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;
  int len=((cCnt - sepCnt) * 6 >> 3) - pad;
  byte[] bytes=new byte[len];
  int d=0;
  for (int cc=0, eLen=(len / 3) * 3; d < eLen; ) {
    int i=IA[chars[sIx++]] << 18 | IA[chars[sIx++]] << 12 | IA[chars[sIx++]] << 6 | IA[chars[sIx++]];
    bytes[d++]=(byte)(i >> 16);
    bytes[d++]=(byte)(i >> 8);
    bytes[d++]=(byte)i;
    if (sepCnt > 0 && ++cc == 19) {
      sIx+=2;
      cc=0;
    }
  }
  if (d < len) {
    int i=0;
    for (int j=0; sIx <= eIx - pad; j++)     i|=IA[chars[sIx++]] << (18 - j * 6);
    for (int r=16; d < len; r-=8)     bytes[d++]=(byte)(i >> r);
  }
  return bytes;
}

/** 
 * @author Mikael Grev Date: 2004-aug-02 Time: 11:31:11
 */
public static byte[] decodeBase64(String chars,int offset,int charsLen){
  if (charsLen == 0) {
    return new byte[0];
  }
  int sIx=offset, eIx=offset + charsLen - 1;
  while (sIx < eIx && IA[chars.charAt(sIx)] < 0)   sIx++;
  while (eIx > 0 && IA[chars.charAt(eIx)] < 0)   eIx--;
  int pad=chars.charAt(eIx) == '=' ? (chars.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
  int cCnt=eIx - sIx + 1;
  int sepCnt=charsLen > 76 ? (chars.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
  int len=((cCnt - sepCnt) * 6 >> 3) - pad;
  byte[] bytes=new byte[len];
  int d=0;
  for (int cc=0, eLen=(len / 3) * 3; d < eLen; ) {
    int i=IA[chars.charAt(sIx++)] << 18 | IA[chars.charAt(sIx++)] << 12 | IA[chars.charAt(sIx++)] << 6 | IA[chars.charAt(sIx++)];
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
    for (int j=0; sIx <= eIx - pad; j++)     i|=IA[chars.charAt(sIx++)] << (18 - j * 6);
    for (int r=16; d < len; r-=8)     bytes[d++]=(byte)(i >> r);
  }
  return bytes;
}

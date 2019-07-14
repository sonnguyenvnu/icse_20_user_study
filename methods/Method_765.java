public static int encodeUTF8(char[] chars,int offset,int len,byte[] bytes){
  int sl=offset + len;
  int dp=0;
  int dlASCII=dp + Math.min(len,bytes.length);
  while (dp < dlASCII && chars[offset] < '\u0080') {
    bytes[dp++]=(byte)chars[offset++];
  }
  while (offset < sl) {
    char c=chars[offset++];
    if (c < 0x80) {
      bytes[dp++]=(byte)c;
    }
 else     if (c < 0x800) {
      bytes[dp++]=(byte)(0xc0 | (c >> 6));
      bytes[dp++]=(byte)(0x80 | (c & 0x3f));
    }
 else     if (c >= '\uD800' && c < ('\uDFFF' + 1)) {
      final int uc;
      int ip=offset - 1;
      if (c >= '\uD800' && c < ('\uDBFF' + 1)) {
        if (sl - ip < 2) {
          uc=-1;
        }
 else {
          char d=chars[ip + 1];
          if (d >= '\uDC00' && d < ('\uDFFF' + 1)) {
            uc=((c << 10) + d) + (0x010000 - ('\uD800' << 10) - '\uDC00');
          }
 else {
            bytes[dp++]=(byte)'?';
            continue;
          }
        }
      }
 else {
        if (c >= '\uDC00' && c < ('\uDFFF' + 1)) {
          bytes[dp++]=(byte)'?';
          continue;
        }
 else {
          uc=c;
        }
      }
      if (uc < 0) {
        bytes[dp++]=(byte)'?';
      }
 else {
        bytes[dp++]=(byte)(0xf0 | ((uc >> 18)));
        bytes[dp++]=(byte)(0x80 | ((uc >> 12) & 0x3f));
        bytes[dp++]=(byte)(0x80 | ((uc >> 6) & 0x3f));
        bytes[dp++]=(byte)(0x80 | (uc & 0x3f));
        offset++;
      }
    }
 else {
      bytes[dp++]=(byte)(0xe0 | ((c >> 12)));
      bytes[dp++]=(byte)(0x80 | ((c >> 6) & 0x3f));
      bytes[dp++]=(byte)(0x80 | (c & 0x3f));
    }
  }
  return dp;
}

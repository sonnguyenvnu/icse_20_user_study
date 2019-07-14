private String readUTF(int index,final int utfLen,final char[] buf){
  int endIndex=index + utfLen;
  byte[] b=this.b;
  int strLen=0;
  int c;
  int st=0;
  char cc=0;
  while (index < endIndex) {
    c=b[index++];
switch (st) {
case 0:
      c=c & 0xFF;
    if (c < 0x80) {
      buf[strLen++]=(char)c;
    }
 else     if (c < 0xE0 && c > 0xBF) {
      cc=(char)(c & 0x1F);
      st=1;
    }
 else {
      cc=(char)(c & 0x0F);
      st=2;
    }
  break;
case 1:
buf[strLen++]=(char)((cc << 6) | (c & 0x3F));
st=0;
break;
case 2:
cc=(char)((cc << 6) | (c & 0x3F));
st=1;
break;
}
}
return new String(buf,0,strLen);
}

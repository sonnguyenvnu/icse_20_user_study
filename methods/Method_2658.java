/** 
 * codes ported from iconv lib in utf8.h utf8_codepointtomb
 */
@Override public int[] toIdList(int codePoint){
  int count;
  if (codePoint < 0x80)   count=1;
 else   if (codePoint < 0x800)   count=2;
 else   if (codePoint < 0x10000)   count=3;
 else   if (codePoint < 0x200000)   count=4;
 else   if (codePoint < 0x4000000)   count=5;
 else   if (codePoint <= 0x7fffffff)   count=6;
 else   return EMPTYLIST;
  int[] r=new int[count];
switch (count) {
case 6:
    r[5]=(char)(0x80 | (codePoint & 0x3f));
  codePoint=codePoint >> 6;
codePoint|=0x4000000;
case 5:
r[4]=(char)(0x80 | (codePoint & 0x3f));
codePoint=codePoint >> 6;
codePoint|=0x200000;
case 4:
r[3]=(char)(0x80 | (codePoint & 0x3f));
codePoint=codePoint >> 6;
codePoint|=0x10000;
case 3:
r[2]=(char)(0x80 | (codePoint & 0x3f));
codePoint=codePoint >> 6;
codePoint|=0x800;
case 2:
r[1]=(char)(0x80 | (codePoint & 0x3f));
codePoint=codePoint >> 6;
codePoint|=0xc0;
case 1:
r[0]=(char)codePoint;
}
return r;
}

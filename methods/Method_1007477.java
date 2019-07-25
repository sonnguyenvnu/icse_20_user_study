private static int typelen(char[] buf,int off){
  int start=off;
switch (buf[off]) {
case 'L':
    while (buf[off++] != ';') {
    }
  return off - start;
case 'B':
case 'C':
case 'D':
case 'F':
case 'I':
case 'J':
case 'S':
case 'Z':
case 'V':
return 1;
case '[':
return typelen(buf,off + 1) + 1;
default :
throw new InternalError("Unknown descriptor type: " + buf[0]);
}
}

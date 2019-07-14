public final void scanString(){
  np=bp;
  hasSpecial=false;
  char ch;
  for (; ; ) {
    ch=next();
    if (ch == '\"') {
      break;
    }
    if (ch == EOI) {
      if (!isEOF()) {
        putChar((char)EOI);
        continue;
      }
      throw new JSONException("unclosed string : " + ch);
    }
    if (ch == '\\') {
      if (!hasSpecial) {
        hasSpecial=true;
        if (sp >= sbuf.length) {
          int newCapcity=sbuf.length * 2;
          if (sp > newCapcity) {
            newCapcity=sp;
          }
          char[] newsbuf=new char[newCapcity];
          System.arraycopy(sbuf,0,newsbuf,0,sbuf.length);
          sbuf=newsbuf;
        }
        copyTo(np + 1,sp,sbuf);
      }
      ch=next();
switch (ch) {
case '0':
        putChar('\0');
      break;
case '1':
    putChar('\1');
  break;
case '2':
putChar('\2');
break;
case '3':
putChar('\3');
break;
case '4':
putChar('\4');
break;
case '5':
putChar('\5');
break;
case '6':
putChar('\6');
break;
case '7':
putChar('\7');
break;
case 'b':
putChar('\b');
break;
case 't':
putChar('\t');
break;
case 'n':
putChar('\n');
break;
case 'v':
putChar('\u000B');
break;
case 'f':
case 'F':
putChar('\f');
break;
case 'r':
putChar('\r');
break;
case '"':
putChar('"');
break;
case '\'':
putChar('\'');
break;
case '/':
putChar('/');
break;
case '\\':
putChar('\\');
break;
case 'x':
char x1=ch=next();
char x2=ch=next();
int x_val=digits[x1] * 16 + digits[x2];
char x_char=(char)x_val;
putChar(x_char);
break;
case 'u':
char u1=ch=next();
char u2=ch=next();
char u3=ch=next();
char u4=ch=next();
int val=Integer.parseInt(new String(new char[]{u1,u2,u3,u4}),16);
putChar((char)val);
break;
default :
this.ch=ch;
throw new JSONException("unclosed string : " + ch);
}
continue;
}
if (!hasSpecial) {
sp++;
continue;
}
if (sp == sbuf.length) {
putChar(ch);
}
 else {
sbuf[sp++]=ch;
}
}
token=JSONToken.LITERAL_STRING;
this.ch=next();
}

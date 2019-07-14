private char nextToken() throws ArrayIndexOutOfBoundsException {
  char char1;
  while ((char1=this.buffer[++position]) <= ' ' || char1 == '/') {
switch (char1) {
case '/':
      char ch=this.buffer[++position];
switch (ch) {
case '/':
      while (true) {
        ch=this.buffer[++position];
        if (ch == '\n') {
          break;
        }
      }
    break;
case '*':
  while (true) {
    ch=this.buffer[++position];
    if (ch == '*') {
      ch=this.buffer[++position];
      if (ch == '/') {
        break;
      }
    }
  }
break;
default :
break;
}
default :
break;
}
}
return this.buffer[position];
}

public String info(){
  StringBuilder buf=new StringBuilder();
  int line=1;
  int column=1;
  for (int i=0; i < bp; ++i, column++) {
    char ch=text.charAt(i);
    if (ch == '\n') {
      column=1;
      line++;
    }
  }
  buf.append("pos ").append(bp).append(", line ").append(line).append(", column ").append(column);
  if (text.length() < 65535) {
    buf.append(text);
  }
 else {
    buf.append(text.substring(0,65535));
  }
  return buf.toString();
}

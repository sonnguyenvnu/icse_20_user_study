public int skipSeparators(String text,int index){
  int length=text.length();
  while (index < length) {
switch (text.charAt(index)) {
case ' ':
case '\t':
case '\n':
case '\r':
      index++;
    break;
default :
  return index;
}
}
return index;
}

static public boolean checkParen(char[] array,int index,int stop){
  while (index < stop) {
switch (array[index]) {
case '(':
      return true;
case ' ':
case '\t':
case '\n':
case '\r':
    index++;
  break;
default :
return false;
}
}
return false;
}

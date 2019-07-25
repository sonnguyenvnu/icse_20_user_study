private int getc(){
  if (lastChar < 0)   if (position < maxlen)   return input.charAt(position++);
 else   return -1;
  int c=lastChar;
  lastChar=-1;
  return c;
}

protected CharSequence textWrap(){
  if (textLen == 0) {
    return CharArraySequence.EMPTY;
  }
  return new String(text,0,textLen);
}

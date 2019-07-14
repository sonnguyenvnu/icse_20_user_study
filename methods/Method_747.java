protected char[] genFieldNameChars(){
  int nameLen=this.name.length();
  char[] name_chars=new char[nameLen + 3];
  this.name.getChars(0,this.name.length(),name_chars,1);
  name_chars[0]='"';
  name_chars[nameLen + 1]='"';
  name_chars[nameLen + 2]=':';
  return name_chars;
}

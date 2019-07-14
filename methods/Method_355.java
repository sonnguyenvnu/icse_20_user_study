private String readUTF8(int index,final char[] buf){
  int item=readUnsignedShort(index);
  String s=strings[item];
  if (s != null) {
    return s;
  }
  index=items[item];
  return strings[item]=readUTF(index + 2,readUnsignedShort(index),buf);
}

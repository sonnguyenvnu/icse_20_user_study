private int scipOverNewLine(byte[] partHeaderBuff,int index){
  while (partHeaderBuff[index] != '\n') {
    index++;
  }
  return ++index;
}

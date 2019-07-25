private void append(char character){
  if (tokenBufferLength == tokenBuffer.length) {
    char[] newBuffer=new char[tokenBuffer.length * 2];
    System.arraycopy(tokenBuffer,0,newBuffer,0,tokenBuffer.length);
    tokenBuffer=newBuffer;
  }
  tokenBuffer[tokenBufferLength++]=character;
}

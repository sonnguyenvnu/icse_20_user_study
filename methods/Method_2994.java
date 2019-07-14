public int bufferSize(){
  if (bufferHead < 0)   return 0;
  return (maxSentenceSize - bufferHead + 1);
}

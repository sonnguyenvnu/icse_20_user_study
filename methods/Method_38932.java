protected void textEmitChars(int from,final int to){
  ensureCapacity(to - from);
  while (from < to) {
    text[textLen++]=input[from++];
  }
}

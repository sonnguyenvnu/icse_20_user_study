@Override public void advance(){
  while (true) {
    if (currentOffset < endOffset) {
      if (buffer.charAt(currentOffset) == '\n') {
        lexemeStart=lexemeEnd;
        currentOffset++;
        lexemeEnd=currentOffset;
        break;
      }
    }
 else {
      lexemeStart=lexemeEnd;
      currentOffset=lexemeEnd=endOffset;
      break;
    }
    currentOffset++;
  }
}

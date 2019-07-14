public final int indexOf(char ch,int startIndex){
  int offset=startIndex - bp;
  for (; ; ++offset) {
    final int index=bp + offset;
    char chLoal=charAt(index);
    if (ch == chLoal) {
      return offset + bp;
    }
    if (chLoal == EOI) {
      return -1;
    }
  }
}

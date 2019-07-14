/** 
 * Walk back from 'index' until the brace that seems to be the beginning of the current block, and return the number of spaces found on that line.
 */
protected int calcBraceIndent(int index,char[] contents){
  int braceDepth=1;
  boolean finished=false;
  while ((index != -1) && (!finished)) {
    if (contents[index] == '}') {
      braceDepth++;
      index--;
    }
 else     if (contents[index] == '{') {
      braceDepth--;
      if (braceDepth == 0) {
        finished=true;
      }
      index--;
    }
 else {
      index--;
    }
  }
  if (!finished)   return -1;
  return calcSpaceCount(index,contents);
}

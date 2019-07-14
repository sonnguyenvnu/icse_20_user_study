/** 
 * Calculate the number of spaces on this line.
 */
protected int calcSpaceCount(int index,char contents[]){
  index=calcLineStart(index,contents);
  int spaceCount=0;
  while ((index < contents.length) && (index >= 0) && (contents[index++] == ' ')) {
    spaceCount++;
  }
  return spaceCount;
}

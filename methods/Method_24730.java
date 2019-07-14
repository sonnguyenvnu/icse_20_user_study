/** 
 * Return the index for the first character on this line.
 */
protected int calcLineStart(int index,char contents[]){
  boolean finished=false;
  while ((index != -1) && (!finished)) {
    if ((contents[index] == 10) || (contents[index] == 13)) {
      finished=true;
    }
 else {
      index--;
    }
  }
  return index + 1;
}

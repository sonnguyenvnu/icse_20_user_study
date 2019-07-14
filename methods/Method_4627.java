/** 
 * Returns the position of the esds box within a parent, or  {@link C#POSITION_UNSET} if no esdsbox is found
 */
private static int findEsdsPosition(ParsableByteArray parent,int position,int size){
  int childAtomPosition=parent.getPosition();
  while (childAtomPosition - position < size) {
    parent.setPosition(childAtomPosition);
    int childAtomSize=parent.readInt();
    Assertions.checkArgument(childAtomSize > 0,"childAtomSize should be positive");
    int childType=parent.readInt();
    if (childType == Atom.TYPE_esds) {
      return childAtomPosition;
    }
    childAtomPosition+=childAtomSize;
  }
  return C.POSITION_UNSET;
}

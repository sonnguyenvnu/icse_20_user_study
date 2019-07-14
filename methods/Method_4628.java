/** 
 * Parses the proj box from sv3d box, as specified by https://github.com/google/spatial-media.
 */
private static byte[] parseProjFromParent(ParsableByteArray parent,int position,int size){
  int childPosition=position + Atom.HEADER_SIZE;
  while (childPosition - position < size) {
    parent.setPosition(childPosition);
    int childAtomSize=parent.readInt();
    int childAtomType=parent.readInt();
    if (childAtomType == Atom.TYPE_proj) {
      return Arrays.copyOfRange(parent.data,childPosition,childPosition + childAtomSize);
    }
    childPosition+=childAtomSize;
  }
  return null;
}

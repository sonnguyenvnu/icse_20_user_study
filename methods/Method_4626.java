private static float parsePaspFromParent(ParsableByteArray parent,int position){
  parent.setPosition(position + Atom.HEADER_SIZE);
  int hSpacing=parent.readUnsignedIntToInt();
  int vSpacing=parent.readUnsignedIntToInt();
  return (float)hSpacing / vSpacing;
}

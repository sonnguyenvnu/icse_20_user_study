private int hashNode(int id){
  int hashValue=0;
  for (; id != 0; id=_nodes.get(id).sibling) {
    int unit=_nodes.get(id).unit();
    byte label=_nodes.get(id).label;
    hashValue^=hash(((label & 0xFF) << 24) ^ unit);
  }
  return hashValue;
}

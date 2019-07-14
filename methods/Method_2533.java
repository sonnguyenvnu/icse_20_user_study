void insert(byte[] key,int value){
  if (value < 0) {
    throw new IllegalArgumentException("failed to insert key: negative value");
  }
  if (key.length == 0) {
    throw new IllegalArgumentException("failed to inset key: zero-length key");
  }
  int id=0;
  int keyPos=0;
  for (; keyPos <= key.length; ++keyPos) {
    int childId=_nodes.get(id).child;
    if (childId == 0) {
      break;
    }
    byte keyLabel=keyPos < key.length ? key[keyPos] : 0;
    if (keyPos < key.length && keyLabel == 0) {
      throw new IllegalArgumentException("failed to insert key: invalid null character");
    }
    byte unitLabel=_nodes.get(childId).label;
    if ((keyLabel & 0xFF) < (unitLabel & 0xFF)) {
      throw new IllegalArgumentException("failed to insert key: wrong key order");
    }
 else     if ((keyLabel & 0xFF) > (unitLabel & 0xFF)) {
      _nodes.get(childId).hasSibling=true;
      flush(childId);
      break;
    }
    id=childId;
  }
  if (keyPos > key.length) {
    return;
  }
  for (; keyPos <= key.length; ++keyPos) {
    byte keyLabel=(keyPos < key.length) ? key[keyPos] : 0;
    int childId=appendNode();
    DawgNode node=_nodes.get(id);
    DawgNode child=_nodes.get(childId);
    if (node.child == 0) {
      child.isState=true;
    }
    child.sibling=node.child;
    child.label=keyLabel;
    node.child=childId;
    _nodeStack.add(childId);
    id=childId;
  }
  _nodes.get(id).setValue(value);
}

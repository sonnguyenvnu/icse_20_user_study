private int appendNode(){
  int id;
  if (_recycleBin.empty()) {
    id=_nodes.size();
    _nodes.add(new DawgNode());
  }
 else {
    id=_recycleBin.get(_recycleBin.size() - 1);
    _nodes.get(id).reset();
    _recycleBin.deleteLast();
  }
  return id;
}

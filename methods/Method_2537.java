private boolean areEqual(int nodeId,int unitId){
  for (int i=_nodes.get(nodeId).sibling; i != 0; i=_nodes.get(i).sibling) {
    if ((_units.get(unitId) & 1) != 1) {
      return false;
    }
    ++unitId;
  }
  if ((_units.get(unitId) & 1) == 1) {
    return false;
  }
  for (int i=nodeId; i != 0; i=_nodes.get(i).sibling, --unitId) {
    if (_nodes.get(i).unit() != _units.get(unitId) || _nodes.get(i).label != _labels.get(unitId)) {
      return false;
    }
  }
  return true;
}

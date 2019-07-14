private void flush(int id){
  while (_nodeStack.get(_nodeStack.size() - 1) != id) {
    int nodeId=_nodeStack.get(_nodeStack.size() - 1);
    _nodeStack.deleteLast();
    if (_numStates >= _table.size() - (_table.size() >>> 2)) {
      expandTable();
    }
    int numSiblings=0;
    for (int i=nodeId; i != 0; i=_nodes.get(i).sibling) {
      ++numSiblings;
    }
    int[] matchHashId=findNode(nodeId);
    int matchId=matchHashId[0];
    int hashId=matchHashId[1];
    if (matchId != 0) {
      _isIntersections.set(matchId,true);
    }
 else {
      int unitId=0;
      for (int i=0; i < numSiblings; ++i) {
        unitId=appendUnit();
      }
      for (int i=nodeId; i != 0; i=_nodes.get(i).sibling) {
        _units.set(unitId,_nodes.get(i).unit());
        _labels.set(unitId,_nodes.get(i).label);
        --unitId;
      }
      matchId=unitId + 1;
      _table.set(hashId,matchId);
      ++_numStates;
    }
    for (int i=nodeId, next; i != 0; i=next) {
      next=_nodes.get(i).sibling;
      freeNode(i);
    }
    _nodes.get(_nodeStack.get(_nodeStack.size() - 1)).child=matchId;
  }
  _nodeStack.deleteLast();
}

private boolean mergeable(Placeable data1,Placeable data2){
  if (data1 == null || data2 == null) {
    return true;
  }
  assert data1 != null && data2 != null;
  if (data1 instanceof ConnectorPuzzleEmpty && data2 instanceof ConnectorPuzzleEmpty) {
    return mergeableCC((ConnectorPuzzleEmpty)data1,(ConnectorPuzzleEmpty)data2);
  }
  if (data1 instanceof ConnectorPuzzleEmpty && data2 instanceof BpmElement) {
    final boolean result=mergeablePuzzleSingle((ConnectorPuzzleEmpty)data1,(BpmElement)data2);
    return result;
  }
  if (data2 instanceof ConnectorPuzzleEmpty && data1 instanceof BpmElement) {
    final boolean result=mergeablePuzzleSingle((BpmElement)data1,(ConnectorPuzzleEmpty)data2);
    return result;
  }
  return false;
}

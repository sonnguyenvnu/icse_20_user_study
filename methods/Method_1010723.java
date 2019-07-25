@Nullable @Override public BracePair find(@NotNull EditorCell selectedCell){
  final Pair<EditorCell,String> matchingLabelAndCell=getMatchingLabelAndCell(selectedCell);
  if (matchingLabelAndCell == null) {
    return null;
  }
  final EditorCell matchingCell=matchingLabelAndCell.o1;
  EditorCell bigCell=CellTraversalUtil.getContainingBigCell(selectedCell);
  EditorCell editorCell=CellFinderUtil.findChildByCondition(bigCell,cell -> isMatchingLabelAndCell(matchingLabelAndCell,cell),true);
  return editorCell != null ? new BracePair(editorCell,matchingCell) : null;
}

private static int caseEndPosition(VisitorState state,JCTree.JCCase caseTree){
  if (caseTree.stats.size() == 1) {
    JCTree.JCStatement only=getOnlyElement(caseTree.stats);
    if (only.hasTag(JCTree.Tag.BLOCK)) {
      BlockTree blockTree=(BlockTree)only;
      return blockTree.getStatements().isEmpty() ? ((JCTree)blockTree).getStartPosition() : state.getEndPosition(getLast(blockTree.getStatements()));
    }
  }
  return state.getEndPosition(caseTree);
}

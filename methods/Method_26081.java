@Override public Description matchTry(TryTree tree,VisitorState state){
  if (tryTreeMatches(tree,state)) {
    List<? extends StatementTree> tryStatements=tree.getBlock().getStatements();
    StatementTree lastTryStatement=tryStatements.get(tryStatements.size() - 1);
    Optional<Fix> assertThrowsFix=AssertThrowsUtils.tryFailToAssertThrows(tree,tryStatements,Optional.empty(),state);
    Fix failFix=addFailCall(tree,lastTryStatement,state);
    return buildDescription(lastTryStatement).addFix(assertThrowsFix).addFix(failFix).build();
  }
 else {
    return Description.NO_MATCH;
  }
}

@Override public Description matchSwitch(SwitchTree tree,VisitorState state){
  Optional<? extends CaseTree> maybeDefault=tree.getCases().stream().filter(c -> c.getExpression() == null).findAny();
  if (!maybeDefault.isPresent()) {
    return NO_MATCH;
  }
  List<CaseTree> defaultStatementGroup=new ArrayList<>();
  Iterator<? extends CaseTree> it=tree.getCases().iterator();
  while (it.hasNext()) {
    CaseTree caseTree=it.next();
    defaultStatementGroup.add(caseTree);
    if (caseTree.getExpression() == null) {
      while (it.hasNext() && caseTree.getStatements().isEmpty()) {
        caseTree=it.next();
        defaultStatementGroup.add(caseTree);
      }
      break;
    }
    if (!caseTree.getStatements().isEmpty()) {
      defaultStatementGroup.clear();
    }
  }
  int idx=defaultStatementGroup.indexOf(maybeDefault.get());
  SuggestedFix.Builder fix=SuggestedFix.builder();
  CaseTree defaultTree=defaultStatementGroup.get(idx);
  if (it.hasNext()) {
    if (!Reachability.canCompleteNormally(getLast(defaultStatementGroup))) {
      int start=((JCTree)defaultStatementGroup.get(0)).getStartPosition();
      int end=state.getEndPosition(getLast(defaultStatementGroup));
      String replacement;
      String source=state.getSourceCode().toString();
      if (idx != defaultStatementGroup.size() - 1) {
        int caseEnd=((JCTree)getLast(defaultStatementGroup).getStatements().get(0)).getStartPosition();
        int cutStart=((JCTree)defaultTree).getStartPosition();
        int cutEnd=state.getEndPosition(defaultTree);
        replacement=source.substring(start,cutStart) + source.substring(cutEnd,caseEnd) + "\n" + source.substring(cutStart,cutEnd) + source.substring(caseEnd,end);
      }
 else {
        replacement=source.substring(start,end);
      }
      CaseTree last=getLast(tree.getCases());
      if (last.getExpression() == null || Reachability.canCompleteNormally(last)) {
        replacement="break;\n" + replacement;
      }
      fix.replace(start,end,"").postfixWith(getLast(tree.getCases()),replacement);
    }
  }
 else   if (idx != defaultStatementGroup.size() - 1) {
    fix.delete(defaultTree);
    CaseTree lastCase=getLast(defaultStatementGroup);
    if (!lastCase.getStatements().isEmpty()) {
      fix.prefixWith(lastCase.getStatements().get(0),state.getSourceForNode(defaultTree));
    }
 else {
      fix.postfixWith(lastCase,state.getSourceForNode(defaultTree));
    }
  }
 else {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(defaultStatementGroup.get(0));
  if (!fix.isEmpty()) {
    description.addFix(fix.build());
  }
  return description.build();
}

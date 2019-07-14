private static String createMessage(MethodTree tree,ImmutableList<MemberWithIndex> overloads,Map<MemberWithIndex,Integer> groups,LineMap lineMap,MemberWithIndex current){
  String ungroupedLines=overloads.stream().filter(o -> !groups.get(o).equals(groups.get(current))).map(t -> lineMap.getLineNumber(((JCTree)t.tree()).getStartPosition())).map(String::valueOf).collect(joining(", "));
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  String name=symbol.isConstructor() ? "Constructor overloads" : String.format("Overloads of '%s'",symbol.getSimpleName());
  return String.format("%s are not grouped together; found ungrouped overloads on line(s): %s",name,ungroupedLines);
}

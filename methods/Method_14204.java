public static ExpressionList getExpressionsList(){
  List<String> starredExpressions=((TopList)ProjectManager.singleton.getPreferenceStore().get("scripting.starred-expressions")).getList();
  return new ExpressionList(starredExpressions.stream().map(e -> new Expression(e)).collect(Collectors.toList()));
}

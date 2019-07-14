@Override public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    List<String> expressions=toExpressionList(ProjectManager.singleton.getPreferenceStore().get("scripting.expressions"));
    Set<String> starredExpressions=new HashSet<String>(((TopList)ProjectManager.singleton.getPreferenceStore().get("scripting.starred-expressions")).getList());
    ExpressionsList expressionsList=new ExpressionsList(expressions.stream().map(s -> new ExpressionState(s,starredExpressions.contains(s))).collect(Collectors.toList()));
    respondJSON(response,expressionsList);
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}

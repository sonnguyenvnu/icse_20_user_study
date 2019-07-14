/** 
 * Prints routes to console.
 */
protected void printRoutes(final int width){
  final ActionsManager actionsManager=webApp.madvocContainer().lookupComponent(ActionsManager.class);
  final List<ActionRuntime> actions=actionsManager.getAllActionRuntimes();
  final Map<String,String> aliases=actionsManager.getAllAliases();
  if (actions.isEmpty()) {
    return;
  }
  final Print print=new Print();
  print.line("Routes",width);
  actions.stream().sorted(Comparator.comparing(actionRuntime -> actionRuntime.getActionPath() + ' ' + actionRuntime.getActionMethod())).forEach(ar -> {
    final String actionMethod=ar.getActionMethod();
    print.out(Chalk256.chalk().yellow(),actionMethod == null ? "*" : actionMethod,7);
    print.space();
    final String signature=ClassUtil.getShortClassName(ProxettaUtil.resolveTargetClass(ar.getActionClass()),2) + '#' + ar.getActionClassMethod().getName();
    print.outLeftRightNewLine(Chalk256.chalk().green(),ar.getActionPath(),Chalk256.chalk().blue(),signature,width - 7 - 1);
  }
);
  if (!aliases.isEmpty()) {
    print.line("Aliases",width);
    actions.stream().sorted(Comparator.comparing(actionRuntime -> actionRuntime.getActionPath() + ' ' + actionRuntime.getActionMethod())).forEach(ar -> {
      final String actionPath=ar.getActionPath();
      for (      final Map.Entry<String,String> entry : aliases.entrySet()) {
        if (entry.getValue().equals(actionPath)) {
          print.space(8);
          print.outLeftRightNewLine(Chalk256.chalk().green(),entry.getValue(),Chalk256.chalk().blue(),entry.getKey(),width - 8);
        }
      }
    }
);
  }
  print.line(width);
}

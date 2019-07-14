public static Map<Variable,Set<Variable>> getVariableDependencies(List<Bound> bounds){
  Map<Variable,Set<Variable>> dependencies=new HashMap<>();
  for (  Variable mentionedVariable : getMentionedVariables(bounds)) {
    Set<Variable> set=new HashSet<>();
    set.add(mentionedVariable);
    dependencies.put(mentionedVariable,set);
  }
  for (  Bound bound : bounds) {
    if (bound.leftVariable() != null && bound.rightHasMentionedVariable()) {
      if (bound.ruleType == EQUALITY || bound.ruleType() == SUBTYPE) {
        dependencies.get(bound.leftVariable()).add(bound.getRightMentionedVariable());
      }
    }
 else     if (bound.leftHasMentionedVariable() && bound.rightVariable() != null) {
      if (bound.ruleType == EQUALITY || bound.ruleType() == SUBTYPE) {
        dependencies.get(bound.getLeftMentionedVariable()).add(bound.rightVariable());
      }
    }
  }
  for (int i=0; i < dependencies.size(); ++i) {
    boolean noMoreChanges=true;
    for (    Map.Entry<Variable,Set<Variable>> entry : dependencies.entrySet()) {
      for (      Variable variable : entry.getValue()) {
        if (entry.getValue().addAll(dependencies.get(variable))) {
          noMoreChanges=false;
        }
      }
    }
    if (noMoreChanges) {
      break;
    }
  }
  return dependencies;
}

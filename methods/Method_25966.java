private String buildErrorMessage(List<ConflictResult> conflicts){
  StringBuilder sb=new StringBuilder(" Assisted parameters of the same type need to have distinct values for the @Assisted" + " annotation. There are conflicts between the annotations on this constructor:");
  for (  ConflictResult conflict : conflicts) {
    sb.append("\n").append(conflict.type());
    if (!conflict.value().isEmpty()) {
      sb.append(", @Assisted(\"").append(conflict.value()).append("\")");
    }
    sb.append(": ");
    List<String> simpleParameterNames=Lists.transform(conflict.parameters(),new Function<VariableTree,String>(){
      @Override public String apply(      VariableTree variableTree){
        return variableTree.getName().toString();
      }
    }
);
    Joiner.on(", ").appendTo(sb,simpleParameterNames);
  }
  return sb.toString();
}

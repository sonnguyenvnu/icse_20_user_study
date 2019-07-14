public String render(PathParams pathParams){
  StringBuilder sb=new StringBuilder();
  for (  String templatePart : templateParts) {
    sb.append('/');
    if (isVariable(templatePart)) {
      String variableName=getVariableName(templatePart);
      String variableValue=pathParams.get(variableName);
      if (variableValue == null) {
        throw new IllegalArgumentException("Path parameter " + variableName + " was not bound");
      }
      sb.append(variableValue);
    }
 else {
      sb.append(templatePart);
    }
  }
  sb.deleteCharAt(0);
  return sb.toString();
}

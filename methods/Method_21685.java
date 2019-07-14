private String fixAlias(String alias){
  return alias.replaceAll("\\[","(").replaceAll("\\]",")");
}

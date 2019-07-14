private String getVariableNames(Iterable<ASTVariableDeclaratorId> iterable){
  Iterator<ASTVariableDeclaratorId> it=iterable.iterator();
  StringBuilder builder=new StringBuilder();
  builder.append(it.next());
  while (it.hasNext()) {
    builder.append(", ").append(it.next());
  }
  return builder.toString();
}

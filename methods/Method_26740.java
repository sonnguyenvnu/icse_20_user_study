@Nullable private List<UStatement> templateStatements(@Nullable List<? extends StatementTree> statements){
  if (statements == null) {
    return null;
  }
  ImmutableList.Builder<UStatement> builder=ImmutableList.builder();
  for (  StatementTree statement : statements) {
    builder.add(template(statement));
  }
  return builder.build();
}

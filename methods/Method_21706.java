public static QueryAction createMultiQueryAction(Client client,MultiQuerySelect multiSelect) throws SqlParseException {
switch (multiSelect.getOperation()) {
case UNION_ALL:
case UNION:
    return new MultiQueryAction(client,multiSelect);
default :
  throw new SqlParseException("only supports union and union all");
}
}

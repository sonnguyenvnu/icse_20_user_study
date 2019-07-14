private void fillTableInJoinRequestBuilder(TableInJoinRequestBuilder requestBuilder,TableOnJoinSelect tableOnJoinSelect) throws SqlParseException {
  List<Field> connectedFields=tableOnJoinSelect.getConnectedFields();
  addFieldsToSelectIfMissing(tableOnJoinSelect,connectedFields);
  requestBuilder.setOriginalSelect(tableOnJoinSelect);
  DefaultQueryAction queryAction=new DefaultQueryAction(client,tableOnJoinSelect);
  queryAction.explain();
  requestBuilder.setRequestBuilder(queryAction.getRequestBuilder());
  requestBuilder.setReturnedFields(tableOnJoinSelect.getSelectedFields());
  requestBuilder.setAlias(tableOnJoinSelect.getAlias());
}

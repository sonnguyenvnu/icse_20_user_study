public MultiQuerySelect parseMultiSelect(SQLUnionQuery query) throws SqlParseException {
  Select firstTableSelect=this.parseSelect((MySqlSelectQueryBlock)query.getLeft());
  Select secondTableSelect=this.parseSelect((MySqlSelectQueryBlock)query.getRight());
  return new MultiQuerySelect(query.getOperator(),firstTableSelect,secondTableSelect);
}

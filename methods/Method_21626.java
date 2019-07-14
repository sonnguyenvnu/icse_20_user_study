public Where findWhere() throws SqlParseException {
  if (where == null) {
    return null;
  }
  Where myWhere=Where.newInstance();
  parseWhere(where,myWhere);
  return myWhere;
}

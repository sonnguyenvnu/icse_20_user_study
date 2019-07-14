/** 
 * zhongshu-comment ?????????in?not in???????????????????Object[]
 * @param targetList
 * @return
 * @throws SqlParseException
 */
private Object[] parseValue(List<SQLExpr> targetList) throws SqlParseException {
  Object[] value=new Object[targetList.size()];
  for (int i=0; i < targetList.size(); i++) {
    value[i]=parseValue(targetList.get(i));
  }
  return value;
}

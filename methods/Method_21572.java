/** 
 * zhongshu-comment ????????private????????????parse()??????? zhongshu-comment ?case when??????????script?if-else??????? case when? CASE WHEN platform_id = 'PC' AND os NOT IN ('??') THEN 'unknown' ELSE os script?if-else? ???case when????WHEN platform_id = 'PC' AND os NOT IN ('??') THEN 'unknown' ??????script? (doc['platform_id'].value=='PC') && (doc['os'].value != '??' )
 * @param where
 * @return
 * @throws SqlParseException
 */
public String explain(Where where) throws SqlParseException {
  List<String> codes=new ArrayList<String>();
  while (where.getWheres().size() == 1) {
    where=where.getWheres().getFirst();
  }
  explainWhere(codes,where);
  String relation=where.getConn().name().equals("AND") ? " && " : " || ";
  return Joiner.on(relation).join(codes);
}

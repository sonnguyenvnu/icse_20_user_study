public String buildLimitQuery(Integer limit,Integer offset,String query){
  if (logger.isDebugEnabled()) {
    logger.info("<<< original input query::{} >>>",query);
  }
  final int len=query.length();
  String parsedQuery=len > 0 && query.endsWith(";") ? query.substring(0,len - 1) : query;
  StringBuilder sb=new StringBuilder();
  sb.append("SELECT * FROM (");
  sb.append(parsedQuery);
  sb.append(") data");
  if (limit != null) {
    sb.append(" LIMIT" + " " + limit);
  }
  if (offset != null) {
    sb.append(" OFFSET" + " " + offset);
  }
  sb.append(";");
  String parsedQueryOut=sb.toString();
  if (logger.isDebugEnabled()) {
    logger.info("<<<Final input query::{} >>>",parsedQueryOut);
  }
  return parsedQueryOut;
}

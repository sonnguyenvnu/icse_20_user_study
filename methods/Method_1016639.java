private QueryResult parse(final MessageUnpacker unpacker){
  QueryResult queryResult=new QueryResult();
  QueryResultModelPath queryResultPath=new QueryResultModelPath();
  queryResultPath.add("queryResult",queryResult);
  try {
    traverse(unpacker,queryResultPath,1);
  }
 catch (  IOException e) {
    throw new InfluxDBException(e);
  }
  return queryResult;
}

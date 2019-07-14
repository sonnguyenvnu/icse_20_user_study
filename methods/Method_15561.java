/** 
 * ?????????
 */
@Override public void close(){
  cacheMap.clear();
  cacheMap=null;
  generatedSQLCount=0;
  cachedSQLCount=0;
  executedSQLCount=0;
}

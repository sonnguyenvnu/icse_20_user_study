/** 
 * ???????object?????requestObject?????
 * @param path object???
 * @param result ??????object
 */
@Override public synchronized void putQueryResult(String path,Object result){
  Log.i(TAG,"\n putQueryResult  valuePath = " + path + "; result = " + result + "\n <<<<<<<<<<<<<<<<<<<<<<<");
  Log.d(TAG,"putQueryResult  queryResultMap.containsKey(valuePath) >> queryResultMap.put(path, result);");
  queryResultMap.put(path,result);
}

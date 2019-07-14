/** 
 * Start the stopwatch.
 */
private void start(String str1,String str2){
  result=(Result)results.get(str1 + str2);
  if (result == null) {
    result=new Result();
    result.object=str1;
    result.name=str2;
    results.put(str1 + str2,result);
    resultList.add(result);
  }
  start=System.currentTimeMillis();
}

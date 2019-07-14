@Benchmark public String stringUtilReplaceShortStringOneMatch(){
  return StringUtil.replace(SHORT_STRING_ONE_MATCH,"'","''");
}

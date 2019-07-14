@Benchmark public String stringUtilReplaceLongStringOneMatch(){
  return StringUtil.replace(LONG_STRING_ONE_MATCH,"'","''");
}

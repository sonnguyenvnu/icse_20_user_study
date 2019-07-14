@Benchmark public String stringUtilReplaceLongStringSeveralMatches(){
  return StringUtil.replace(LONG_STRING_SEVERAL_MATCHES,"'","''");
}

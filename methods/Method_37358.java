@Benchmark public String stringReplaceLongStringSeveralMatches(){
  return LONG_STRING_SEVERAL_MATCHES.replace("'","''");
}

@Benchmark public String stringReplaceLongStringOneMatch(){
  return LONG_STRING_ONE_MATCH.replace("'","''");
}

@Benchmark public String stringReplaceShortStringOneMatch(){
  return SHORT_STRING_ONE_MATCH.replace("'","''");
}

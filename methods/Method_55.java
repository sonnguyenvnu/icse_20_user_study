public List<String> generateAbbreviations(String word){
  List<String> result=new ArrayList<String>();
  backtrack(result,word,0,"",0);
  return result;
}

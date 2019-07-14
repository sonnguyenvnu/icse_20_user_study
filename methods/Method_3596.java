private static List<String> pick_sentences(List<String> resultList,int max_length){
  List<String> summary=new ArrayList<String>();
  int count=0;
  for (  String result : resultList) {
    if (count + result.length() <= max_length) {
      summary.add(result);
      count+=result.length();
    }
  }
  return summary;
}

public void letterCombinationsRecursive(List<String> result,String digits,String current,int index,String[] mapping){
  if (index == digits.length()) {
    result.add(current);
    return;
  }
  String letters=mapping[digits.charAt(index) - '0'];
  for (int i=0; i < letters.length(); i++) {
    letterCombinationsRecursive(result,digits,current + letters.charAt(i),index + 1,mapping);
  }
}

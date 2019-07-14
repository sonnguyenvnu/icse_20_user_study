public List<String> letterCombinations(String digits){
  if (digits.length() == 0)   return Collections.emptyList();
  LinkedList<String> list=new LinkedList<>();
  list.add("");
  char[] charArray=digits.toCharArray();
  for (int i=0; i < charArray.length; i++) {
    char c=charArray[i];
    while (list.getFirst().length() == i) {
      String pop=list.removeFirst();
      for (      char v : map[c - '2'].toCharArray()) {
        list.addLast(pop + v);
      }
    }
  }
  return list;
}

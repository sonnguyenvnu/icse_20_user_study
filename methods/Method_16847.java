public List<String> generateParenthesis(int n){
  HashMap<Integer,List<String>> hashMap=new HashMap<>();
  hashMap.put(0,Collections.singletonList(""));
  for (int i=1; i <= n; i++) {
    List<String> list=new ArrayList<>();
    for (int j=0; j < i; j++) {
      for (      String fj : hashMap.get(j)) {
        for (        String fi_j_1 : hashMap.get(i - j - 1)) {
          list.add("(" + fj + ")" + fi_j_1);
        }
      }
    }
    hashMap.put(i,list);
  }
  return hashMap.get(n);
}

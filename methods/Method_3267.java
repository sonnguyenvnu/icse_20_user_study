public void recoverKeyValue(){
  key=new ArrayList<String>();
  List<Integer> val1=new ArrayList<Integer>();
  HashMap<Integer,List<Integer>> childIdxMap=new HashMap<Integer,List<Integer>>();
  for (int i=0; i < check.length; i++) {
    if (check[i] <= 0)     continue;
    if (!childIdxMap.containsKey(check[i])) {
      List<Integer> childList=new ArrayList<Integer>();
      childIdxMap.put(check[i],childList);
    }
    childIdxMap.get(check[i]).add(i);
  }
  Stack<Integer[]> s=new Stack<Integer[]>();
  s.add(new Integer[]{1,-1});
  List<Integer> charBuf=new ArrayList<Integer>();
  while (true) {
    Integer[] pair=s.peek();
    List<Integer> childList=childIdxMap.get(pair[0]);
    if (childList == null || (childList.size() - 1) == pair[1]) {
      s.pop();
      if (s.empty()) {
        break;
      }
 else {
        if (!charBuf.isEmpty()) {
          charBuf.remove(charBuf.size() - 1);
        }
        continue;
      }
    }
 else {
      pair[1]++;
    }
    int c=(int)childList.get(pair[1]);
    int code=(c - 1 - pair[0]);
    if (base[c] > 0) {
      s.add(new Integer[]{base[c],-1});
      charBuf.add(code);
      continue;
    }
 else     if (base[c] < 0) {
      if (check[c] == c) {
        char[] chars=new char[charBuf.size()];
        for (int i=0; i < charBuf.size(); i++) {
          chars[i]=(char)(int)charBuf.get(i);
        }
        key.add(new String(chars));
        val1.add(-base[c] - 1);
      }
      continue;
    }
  }
  if (!val1.isEmpty()) {
    value=new int[val1.size()];
    for (int i=0; i < val1.size(); i++) {
      value[i]=val1.get(i);
    }
  }
}

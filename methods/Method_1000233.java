/** 
 * Bfs
 * @param beginWord begin word
 * @param endWord end word
 * @param wordList wordlist
 */
private void bfs(String beginWord,String endWord,List<String> wordList){
  queue.offer(beginWord);
  minDistance.put(beginWord,0);
  while (!queue.isEmpty()) {
    String currWord=queue.poll();
    StringBuilder childSb=new StringBuilder(currWord);
    for (int j=0, ln=childSb.length(); j < ln; j++) {
      for (int i=0, l=CONST.length(); i < l; i++) {
        char old=childSb.charAt(j);
        childSb.replace(j,j + 1,String.valueOf(CONST.charAt(i)));
        String child=childSb.toString();
        if (dictionary.contains(child)) {
          if (minDistance.get(child) == null) {
            minDistance.put(child,minDistance.get(currWord) + 1);
            addChild(currWord,child);
            if (!child.equals(endWord))             queue.offer(child);
          }
 else {
            if (minDistance.get(child) == (minDistance.get(currWord) + 1))             addChild(currWord,child);
          }
        }
        childSb.replace(j,j + 1,String.valueOf(old));
      }
    }
  }
}

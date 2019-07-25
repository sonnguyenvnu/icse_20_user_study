/** 
 * Accept input and return hot-text for the current char
 * @param c char
 * @return List of top 3 hot-text
 */
public List<String> input(char c){
  List<String> result=new ArrayList<>();
  if (c == '#') {
    String sentence=currSentence.toString();
    if (hotTextMap.containsKey(sentence)) {
      hotTextMap.put(sentence,hotTextMap.get(sentence) + 1);
      trie.update(sentence);
    }
 else {
      hotTextMap.put(sentence,1);
      trie.insert(sentence);
      trie.update(sentence);
    }
    currSentence=new StringBuilder();
    curr=root;
  }
 else {
    if (curr != null) {
      if (curr.containsChild(c)) {
        List<String> hotText=curr.getSubtrie(c).getTop3HotText();
        hotText.stream().forEach((x) -> result.add(currSentence.toString() + x));
        curr=curr.getSubtrie(c);
      }
 else {
        curr=null;
      }
    }
    currSentence.append(c);
  }
  return result;
}

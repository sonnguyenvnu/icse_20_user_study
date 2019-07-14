/** 
 * ????
 * @param text      ??
 * @param processor ???
 */
public void parseLongestText(char[] text,AhoCorasickDoubleArrayTrie.IHit<V> processor){
  int length=text.length;
  for (int i=0; i < length; ++i) {
    BaseNode<V> state=transition(text[i]);
    if (state != null) {
      int to=i + 1;
      int end=to;
      V value=state.getValue();
      for (; to < length; ++to) {
        state=state.transition(text[to]);
        if (state == null)         break;
        if (state.getValue() != null) {
          value=state.getValue();
          end=to + 1;
        }
      }
      if (value != null) {
        processor.hit(i,end,value);
        i=end - 1;
      }
    }
  }
}

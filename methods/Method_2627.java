/** 
 * ????
 * @param text      ??
 * @param processor ???
 */
public void parseText(char[] text,AhoCorasickDoubleArrayTrie.IHit<V> processor){
  int length=text.length;
  int begin=0;
  BaseNode<V> state=this;
  for (int i=begin; i < length; ++i) {
    state=state.transition(text[i]);
    if (state != null) {
      V value=state.getValue();
      if (value != null) {
        processor.hit(begin,i + 1,value);
      }
    }
 else {
      i=begin;
      ++begin;
      state=this;
    }
  }
}

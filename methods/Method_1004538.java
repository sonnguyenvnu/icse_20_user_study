/** 
 * Parses a  {@link CharSequence} argument and returns a {@link HumanTime} instance.
 * @param s the char sequence, may not be <code>null</code>
 * @return an instance, never <code>null</code>
 */
public static HumanTime eval(final CharSequence s){
  HumanTime out=new HumanTime(0L);
  int num=0;
  int start=0;
  int end=0;
  State oldState=State.IGNORED;
  for (  char c : new Iterable<Character>(){
    /** 
 * @see java.lang.Iterable#iterator()
 */
    public Iterator<Character> iterator(){
      return new Iterator<Character>(){
        /** 
 * @see java.util.Iterator#hasNext()
 */
        public boolean hasNext(){
          return p < s.length();
        }
        /** 
 * @see java.util.Iterator#next()
 */
        public Character next(){
          return s.charAt(p++);
        }
        /** 
 * @see java.util.Iterator#remove()
 */
        public void remove(){
          throw new UnsupportedOperationException();
        }
      }
;
    }
  }
) {
    State newState=getState(c);
    if (oldState != newState) {
      if (oldState == State.NUMBER && (newState == State.IGNORED || newState == State.UNIT)) {
        num=Integer.parseInt(s.subSequence(start,end).toString());
      }
 else       if (oldState == State.UNIT && (newState == State.IGNORED || newState == State.NUMBER)) {
        out.nTimes(s.subSequence(start,end).toString(),num);
        num=0;
      }
      start=end;
    }
    ++end;
    oldState=newState;
  }
  if (oldState == State.UNIT) {
    out.nTimes(s.subSequence(start,end).toString(),num);
  }
  return out;
}

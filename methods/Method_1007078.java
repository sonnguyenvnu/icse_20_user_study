/** 
 * Constructs a lazy string from a String.
 * @param s A string from which to construct a lazy string.
 * @return A lazy string with the characters from the given string.
 */
public static LazyString str(final String s){
  return new LazyString(Stream.unfold(o -> {
    final String s2=o._1();
    final int n=o._2();
    final Option<P2<Character,P2<String,Integer>>> none=none();
    return s2.length() <= n ? none : some(p(s2.charAt(n),p(s2,n + 1)));
  }
,p(s,0)));
}

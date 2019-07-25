/** 
 * Provides a transformation to lift any function so that it maps over Trees.
 * @return A transformation to lift any function so that it maps over Trees.
 */
public static <A,B>F<F<A,B>,F<Tree<A>,Tree<B>>> fmap_(){
  return f -> a -> a.fmap(f);
}

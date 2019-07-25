/** 
 * Sends the given input value to both argument functions and combines their output.
 * @param f A function to receive an input value.
 * @param g A function to receive an input value.
 * @param b An input value to send to both functions.
 * @return The product of the two functions applied to the input value.
 */
public static <B,C,D>P2<C,D> fanout(final F<B,C> f,final F<B,D> g,final B b){
  return join(P.<B,B>p2()).f(b).split(f,g);
}

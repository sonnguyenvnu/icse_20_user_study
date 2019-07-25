/** 
 * @return Function that extracts {@linkplain #o1 first value} from a pair
 */
public static <P1,P2>Function<Pair<P1,P2>,P1> first(){
  return p -> p.o1;
}

/** 
 * @return Function that extracts {@linkplain #o2 second value} from a pair
 */
public static <P1,P2>Function<Pair<P1,P2>,P2> second(){
  return p -> p.o2;
}

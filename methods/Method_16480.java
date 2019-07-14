/** 
 * ?????????????ALl??????true <pre> direction=ALL; matchDirection("A") -> true matchDirection("ALL") -> true matchDirection("R") -> true matchDirection("P") -> true matchDirection("O") -> false </pre> <p> <pre> direction=POSITIVE; matchDirection("P") -> true matchDirection("POS") -> true matchDirection("A") -> false matchDirection("O") -> false </pre>
 * @param direction ????????
 * @return ????
 * @see Direction#fromString(String)
 */
default boolean matchDirection(String direction){
  return matchDirection(Direction.fromString(direction));
}

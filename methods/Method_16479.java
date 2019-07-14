/** 
 * ?????????????ALl??????true <pre> direction=ALL; matchDirection(POSITIVE) -> true matchDirection(REVERSE) -> true matchDirection(ALL) -> true </pre> <p> <pre> direction=POSITIVE; matchDirection(POSITIVE) -> true matchDirection(REVERSE) -> false matchDirection(ALL) -> false </pre>
 * @param direction ????????
 * @return ????
 */
default boolean matchDirection(Direction direction){
  return getDirection() == Direction.ALL || getDirection() == direction;
}

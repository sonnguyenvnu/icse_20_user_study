/** 
 * Returns a NonEmptyList of the tails of this list. A list is considered a tail of itself for the purpose of this function (Comonad pattern).
 * @return A NonEmptyList of the tails of this list.
 */
public NonEmptyList<NonEmptyList<A>> tails(){
  return fromList(somes(toList().tails().map(NonEmptyList::fromList))).some();
}

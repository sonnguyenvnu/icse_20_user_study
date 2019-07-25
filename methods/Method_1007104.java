/** 
 * Returns a NonEmptyList of the sublists of this list.
 * @return a NonEmptyList of the sublists of this list.
 */
public NonEmptyList<NonEmptyList<A>> sublists(){
  return fromList(somes(toList().toStream().substreams().map(F1Functions.o(NonEmptyList::fromList,Conversions.Stream_List())).toList())).some();
}

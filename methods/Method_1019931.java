/** 
 * Nest this index deeper by one element.
 * @param < NewHead > the type of the preceding element
 * @return an index at the same Target, nested one level deep
 */
public final <NewHead>Index<Target,HCons<NewHead,? extends TargetList>> after(){
  return new N<>(this);
}

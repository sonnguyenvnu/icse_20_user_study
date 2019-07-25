/** 
 * Measures the footprint of the specified object graph. The object graph is defined by a root object and whatever object can be reached through that, excluding static fields,  {@code Class}objects, and fields defined in  {@code enum}s (all these are considered shared values, which should not contribute to the cost of any single object graph), and any object for which the user-provided predicate returns  {@code false}.
 * @param rootObject the root object of the object graph
 * @param objectAcceptor a predicate that returns {@code true} for objects to be explored (andtreated as part of the footprint), or  {@code false} to forbid the traversal to traverse thegiven object
 * @return the footprint of the object graph
 */
public static Footprint measure(Object rootObject,Predicate<Object> objectAcceptor){
  Preconditions.checkNotNull(objectAcceptor,"predicate");
  Predicate<Chain> completePredicate=Predicates.and(ImmutableList.of(ObjectExplorer.notEnumFieldsOrClasses,new ObjectExplorer.AtMostOncePredicate(),Predicates.compose(objectAcceptor,ObjectExplorer.chainToObject)));
  return ObjectExplorer.exploreObject(rootObject,new ObjectGraphVisitor(completePredicate),EnumSet.of(Feature.VISIT_PRIMITIVES,Feature.VISIT_NULL));
}

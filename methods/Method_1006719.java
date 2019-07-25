/** 
 * Find the union of this  {@link ClassInfoList} with one or more others.
 * @param others The other  {@link ClassInfoList}s to union with this one.
 * @return The union of this {@link ClassInfoList} with the others.
 */
public ClassInfoList union(final ClassInfoList... others){
  final Set<ClassInfo> reachableClassesUnion=new LinkedHashSet<>(this);
  final Set<ClassInfo> directlyRelatedClassesUnion=new LinkedHashSet<>(directlyRelatedClasses);
  for (  final ClassInfoList other : others) {
    reachableClassesUnion.addAll(other);
    directlyRelatedClassesUnion.addAll(other.directlyRelatedClasses);
  }
  return new ClassInfoList(reachableClassesUnion,directlyRelatedClassesUnion,sortByName);
}

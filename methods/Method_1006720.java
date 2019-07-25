/** 
 * Find the subset of this  {@link ClassInfoList} for which the given filter predicate is true.
 * @param filter The  {@link ClassInfoFilter} to apply.
 * @return The subset of this {@link ClassInfoList} for which the given filter predicate is true.
 */
public ClassInfoList filter(final ClassInfoFilter filter){
  final Set<ClassInfo> reachableClassesFiltered=new LinkedHashSet<>(size());
  final Set<ClassInfo> directlyRelatedClassesFiltered=new LinkedHashSet<>(directlyRelatedClasses.size());
  for (  final ClassInfo ci : this) {
    if (filter.accept(ci)) {
      reachableClassesFiltered.add(ci);
      if (directlyRelatedClasses.contains(ci)) {
        directlyRelatedClassesFiltered.add(ci);
      }
    }
  }
  return new ClassInfoList(reachableClassesFiltered,directlyRelatedClassesFiltered,sortByName);
}

/** 
 * Find the subset of the  {@link ModuleInfo} objects in this list for which the given filter predicate is true.
 * @param filter The  {@link ModuleInfoFilter} to apply.
 * @return The subset of the {@link ModuleInfo} objects in this list for which the given filter predicate istrue.
 */
public ModuleInfoList filter(final ModuleInfoFilter filter){
  final ModuleInfoList moduleInfoFiltered=new ModuleInfoList();
  for (  final ModuleInfo resource : this) {
    if (filter.accept(resource)) {
      moduleInfoFiltered.add(resource);
    }
  }
  return moduleInfoFiltered;
}

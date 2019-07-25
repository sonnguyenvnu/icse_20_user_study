/** 
 * TODO: remove parent argument, also in  {@link DependencyCollector}. 
 */
public Set<BindingKey> calculate(TypeElement eitherComponent,@Nullable TypeElement parentEitherComponent){
  if (eitherComponentToKeyMap.containsKey(eitherComponent)) {
    return eitherComponentToKeyMap.get(eitherComponent);
  }
  Set<BindingKey> unresolved=new HashSet<>();
  SetMultimap<BindingKey,DependencyInfo> dependencies=DependencyCollector.collectionToMultimap(dependencyCollector.collectForOne(eitherComponent,parentEitherComponent,unresolved));
  logger.n("(sub)component: %s unresolved collected: %s",eitherComponent,unresolved);
  Set<BindingKey> fromChildrenAndPackages=new HashSet<>();
  for (  TypeElement child : eitherComponentToChildrenMap.get(eitherComponent)) {
    fromChildrenAndPackages.addAll(calculate(child,eitherComponent));
  }
  for (  BindingKey k : fromChildrenAndPackages) {
    Set<DependencyInfo> dependencyInfos=utils.getDependencyInfo(dependencies,k);
    boolean resolvable=dependencyInfos != null;
    logKey(eitherComponent,"key: %s, resovable %s, di: %s",k,resolvable,dependencyInfos);
    if (!resolvable) {
      unresolved.add(k);
    }
  }
  eitherComponentToKeyMap.put(eitherComponent,unresolved);
  logger.w("(sub)component: %s\ndependencies: %s\nunresolved: %s",eitherComponent,dependencies,unresolved);
  return unresolved;
}

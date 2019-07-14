private Set<Method> overriddenMethodsRec(Class<?> exploredType,boolean skip,Set<Method> candidates,Set<Method> result,Set<Class<?>> alreadyExplored,boolean onlyPublic){
  if (candidates.isEmpty() || alreadyExplored.contains(exploredType)) {
    return result;
  }
  alreadyExplored.add(exploredType);
  if (!skip) {
    Set<Method> toRemove=new HashSet<>();
    for (    Method dm : exploredType.getDeclaredMethods()) {
      if (onlyPublic && !Modifier.isPublic(dm.getModifiers()) || Modifier.isPrivate(dm.getModifiers()) || Modifier.isStatic(dm.getModifiers())) {
        continue;
      }
      for (      Method cand : candidates) {
        if (Modifier.isPrivate(dm.getModifiers()) || Modifier.isStatic(dm.getModifiers())) {
          continue;
        }
        if (cand.getName().equals(dm.getName()) && Arrays.equals(cand.getParameterTypes(),dm.getParameterTypes())) {
          result.add(cand);
          toRemove.add(cand);
        }
      }
      candidates.removeAll(toRemove);
    }
  }
  if (candidates.isEmpty()) {
    return result;
  }
  Class<?> superClass=exploredType.getSuperclass();
  if (superClass != null) {
    overriddenMethodsRec(superClass,false,candidates,result,alreadyExplored,false);
  }
  for (  Class<?> iface : exploredType.getInterfaces()) {
    overriddenMethodsRec(iface,false,candidates,result,alreadyExplored,false);
  }
  if (exploredType.isInterface() && exploredType.getInterfaces().length == 0) {
    if (!alreadyExplored.contains(Object.class)) {
      overriddenMethodsRec(Object.class,false,candidates,result,alreadyExplored,true);
    }
  }
  return result;
}

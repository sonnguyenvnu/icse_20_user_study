private static void propagateNonStaticness(Map<MethodSymbol,MethodDetails> nodes){
  for (  Map.Entry<MethodSymbol,MethodDetails> entry : nodes.entrySet()) {
    MethodSymbol sym=entry.getKey();
    MethodDetails methodDetails=entry.getValue();
    for (    MethodSymbol use : methodDetails.methodsReferenced) {
      if (nodes.containsKey(use)) {
        nodes.get(use).referencedBy.add(sym);
      }
    }
    if (referencesExternalMethods(methodDetails,nodes.keySet())) {
      methodDetails.couldPossiblyBeStatic=false;
    }
  }
  Set<MethodSymbol> toVisit=new HashSet<>(nodes.keySet());
  while (!toVisit.isEmpty()) {
    Set<MethodSymbol> nextVisit=new HashSet<>();
    for (    MethodSymbol sym : toVisit) {
      MethodDetails methodDetails=nodes.get(sym);
      if (methodDetails.couldPossiblyBeStatic) {
        continue;
      }
      for (      MethodSymbol user : methodDetails.referencedBy) {
        if (!nodes.get(user).couldPossiblyBeStatic) {
          continue;
        }
        nodes.get(user).couldPossiblyBeStatic=false;
        nodes.get(user).methodsReferenced.remove(sym);
        nextVisit.add(user);
      }
    }
    toVisit=nextVisit;
  }
}

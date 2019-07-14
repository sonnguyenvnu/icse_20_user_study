@Override public Description matchClass(ClassTree tree,VisitorState state){
  ClassSymbol origin=getSymbol(tree);
  Types types=state.getTypes();
  Multimap<String,MethodSymbol> methods=HashMultimap.create();
  for (  Symbol sym : types.membersClosure(getType(tree),false).getSymbols()) {
    if (!(sym instanceof MethodSymbol)) {
      continue;
    }
    MethodSymbol msym=(MethodSymbol)sym;
    if (msym.getParameters().stream().noneMatch(p -> maybeFunctionalInterface(p.type,types,state))) {
      continue;
    }
    if (msym.isConstructor() && !msym.owner.equals(origin)) {
      continue;
    }
    methods.put(functionalInterfaceSignature(state,msym),msym);
  }
  for (  Tree member : tree.getMembers()) {
    if (!(member instanceof MethodTree)) {
      continue;
    }
    MethodSymbol msym=getSymbol((MethodTree)member);
    if (msym.getParameters().stream().noneMatch(p -> maybeFunctionalInterface(p.type,types,state))) {
      continue;
    }
    Collection<MethodSymbol> clash=new ArrayList<>(methods.removeAll(functionalInterfaceSignature(state,msym)));
    Deque<MethodSymbol> worklist=new ArrayDeque<>();
    worklist.push(msym);
    clash.remove(msym);
    while (!worklist.isEmpty()) {
      MethodSymbol msym2=worklist.removeFirst();
      ImmutableList<MethodSymbol> overrides=clash.stream().filter(m -> msym2.overrides(m,origin,types,false)).collect(toImmutableList());
      worklist.addAll(overrides);
      clash.removeAll(overrides);
    }
    if (!clash.isEmpty()) {
      if (ASTHelpers.findSuperMethod(msym,types).isPresent() && clash.stream().anyMatch(methodSymbol -> ASTHelpers.findSuperMethod(methodSymbol,types).isPresent())) {
        continue;
      }
      if (isSuppressed(member)) {
        continue;
      }
      String message="When passing lambda arguments to this function, callers will need a cast to" + " disambiguate with: " + clash.stream().map(m -> "\n    " + Signatures.prettyMethodSignature(origin,m)).collect(joining(""));
      state.reportMatch(buildDescription(member).setMessage(message).build());
    }
  }
  return NO_MATCH;
}

/** 
 * Looks for a field or method with the given  {@code identifier}, in
 * @code typeSym} or one of it's super-types or super-interfaces, and that is visible from the{@code start} symbol.
 */
private static ImmutableSet<Symbol> lookup(Symbol.TypeSymbol typeSym,Symbol.TypeSymbol start,Name identifier,Types types,Symbol.PackageSymbol pkg){
  if (typeSym == null) {
    return ImmutableSet.of();
  }
  ImmutableSet.Builder<Symbol> members=ImmutableSet.builder();
  members.addAll(lookup(types.supertype(typeSym.type).tsym,start,identifier,types,pkg));
  for (  Type i : types.interfaces(typeSym.type)) {
    members.addAll(lookup(i.tsym,start,identifier,types,pkg));
  }
  OUTER:   for (  Symbol member : typeSym.members().getSymbolsByName(identifier)) {
    if (!member.isStatic()) {
      continue;
    }
switch ((int)(member.flags() & Flags.AccessFlags)) {
case Flags.PRIVATE:
      continue OUTER;
case 0:
case Flags.PROTECTED:
    if (member.packge() != pkg) {
      continue OUTER;
    }
  break;
case Flags.PUBLIC:
default :
break;
}
if (member.isMemberOf(start,types)) {
members.add(member);
}
}
return members.build();
}

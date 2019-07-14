private static boolean isEqualsMethod(MemberName calleeName,List<Node> arguments,Types types,Symtab symtab){
  if (!calleeName.member.equals("equals") || arguments.size() != 1) {
    return false;
  }
  if (!(getOnlyElement(arguments).getTree() instanceof JCIdent)) {
    return false;
  }
  Symbol sym=((JCIdent)getOnlyElement(arguments).getTree()).sym;
  if (sym == null || sym.type == null) {
    return false;
  }
  return types.isSameType(sym.type,symtab.objectType) && (!variablesAtIndexes(ImmutableSet.of(0),arguments).isEmpty());
}

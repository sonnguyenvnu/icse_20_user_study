/** 
 * @return all values of the given enum type, in declaration order. 
 */
public static LinkedHashSet<String> enumValues(TypeSymbol enumType){
  if (enumType.getKind() != ElementKind.ENUM) {
    throw new IllegalStateException();
  }
  Scope scope=enumType.members();
  Deque<String> values=new ArrayDeque<>();
  for (  Symbol sym : scope.getSymbols()) {
    if (sym instanceof VarSymbol) {
      VarSymbol var=(VarSymbol)sym;
      if ((var.flags() & Flags.ENUM) != 0) {
        values.push(sym.name.toString());
      }
    }
  }
  return new LinkedHashSet<>(values);
}

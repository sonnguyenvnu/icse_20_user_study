private static String buildMessage(List<Symbol> shadowedTypes){
  return "Found type parameters shadowing other declared types:\n\t" + shadowedTypes.stream().map(sym -> "Type parameter " + sym.getSimpleName() + " shadows visible type " + sym.flatName()).collect(Collectors.joining("\n\t"));
}

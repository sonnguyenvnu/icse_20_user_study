private static boolean isAcceptableImport(Symbol symbol,Set<String> badNames){
  Name simpleName=symbol.getSimpleName();
  return badNames.stream().noneMatch(simpleName::contentEquals);
}

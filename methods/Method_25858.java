private static String getDoNotCallValue(Symbol symbol){
  for (  Attribute.Compound a : symbol.getRawAttributes()) {
    if (!a.type.tsym.getQualifiedName().contentEquals(DO_NOT_CALL)) {
      continue;
    }
    return MoreAnnotations.getValue(a,"value").flatMap(MoreAnnotations::asStringValue).orElse("");
  }
  throw new IllegalStateException();
}

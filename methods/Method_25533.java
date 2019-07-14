private static boolean isFinal(Symbol symbol){
  return (symbol.flags() & Flags.FINAL) == Flags.FINAL;
}

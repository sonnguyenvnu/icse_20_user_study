private static boolean effectivelyFinal(Symbol symbol){
  return (symbol.flags() & (Flags.FINAL | Flags.EFFECTIVELY_FINAL)) != 0;
}

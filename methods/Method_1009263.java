@Contract(pure=true) @NotNull public static Operator operator(@NotNull final ElixirStabOperation stabOperation){
  return stabOperation.getStabInfixOperator();
}

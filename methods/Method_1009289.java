private boolean execute(@NotNull ElixirStabNoParenthesesSignature match,@NotNull ResolveState state){
  return execute(match.getNoParenthesesArguments(),state);
}

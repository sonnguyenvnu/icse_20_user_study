@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull ElixirAccessExpression accessExpression){
  return QuotableImpl.quote(accessExpression);
}

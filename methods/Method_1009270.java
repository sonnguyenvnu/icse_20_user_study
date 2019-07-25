@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull final ElixirStabNoParenthesesSignature stabNoParenthesesSignature){
  return QuotableImpl.quote(stabNoParenthesesSignature);
}

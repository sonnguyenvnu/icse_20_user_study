@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull UnqualifiedNoParenthesesCall unqualifiedNoParenthesesCall){
  return QuotableImpl.quote(unqualifiedNoParenthesesCall);
}

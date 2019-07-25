@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull QualifiedNoParenthesesCall qualifiedNoParenthesesCall){
  return QuotableImpl.quote(qualifiedNoParenthesesCall);
}

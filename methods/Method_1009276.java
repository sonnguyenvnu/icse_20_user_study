@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull QualifiedParenthesesCall qualifiedParenthesesCall){
  return QuotableImpl.quote(qualifiedParenthesesCall);
}

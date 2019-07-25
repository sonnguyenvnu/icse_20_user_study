@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull UnqualifiedBracketOperation unqualifiedBracketOperation){
  return QuotableImpl.quote(unqualifiedBracketOperation);
}

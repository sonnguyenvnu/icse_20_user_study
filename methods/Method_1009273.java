@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull QualifiedMultipleAliases qualifiedMultipleAliases){
  return QuotableImpl.quote(qualifiedMultipleAliases);
}

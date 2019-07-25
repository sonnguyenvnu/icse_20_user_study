@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull Sigil sigil,@NotNull OtpErlangObject quotedContent){
  return QuotableImpl.quote(sigil,quotedContent);
}

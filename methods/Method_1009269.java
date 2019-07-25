@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull final ElixirStabBody stabBody){
  return QuotableImpl.quote(stabBody);
}

@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull final ElixirMapUpdateArguments mapUpdateArguments){
  return QuotableImpl.quote(mapUpdateArguments);
}

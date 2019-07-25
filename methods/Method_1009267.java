@Contract(pure=true) @NotNull public static OtpErlangObject quote(@NotNull ElixirContainerAssociationOperation containerAssociationOperation){
  return QuotableImpl.quote(containerAssociationOperation);
}

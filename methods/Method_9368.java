private boolean isPersonalDocument(TLRPC.SecureValueType type){
  return type instanceof TLRPC.TL_secureValueTypeDriverLicense || type instanceof TLRPC.TL_secureValueTypePassport || type instanceof TLRPC.TL_secureValueTypeInternalPassport || type instanceof TLRPC.TL_secureValueTypeIdentityCard;
}

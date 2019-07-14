private boolean isAddressDocument(TLRPC.SecureValueType type){
  return type instanceof TLRPC.TL_secureValueTypeUtilityBill || type instanceof TLRPC.TL_secureValueTypeBankStatement || type instanceof TLRPC.TL_secureValueTypePassportRegistration || type instanceof TLRPC.TL_secureValueTypeTemporaryRegistration || type instanceof TLRPC.TL_secureValueTypeRentalAgreement;
}

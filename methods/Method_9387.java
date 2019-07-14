private String getTextForType(TLRPC.SecureValueType type){
  if (type instanceof TLRPC.TL_secureValueTypePassport) {
    return LocaleController.getString("ActionBotDocumentPassport",R.string.ActionBotDocumentPassport);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
    return LocaleController.getString("ActionBotDocumentDriverLicence",R.string.ActionBotDocumentDriverLicence);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
    return LocaleController.getString("ActionBotDocumentIdentityCard",R.string.ActionBotDocumentIdentityCard);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeUtilityBill) {
    return LocaleController.getString("ActionBotDocumentUtilityBill",R.string.ActionBotDocumentUtilityBill);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeBankStatement) {
    return LocaleController.getString("ActionBotDocumentBankStatement",R.string.ActionBotDocumentBankStatement);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeRentalAgreement) {
    return LocaleController.getString("ActionBotDocumentRentalAgreement",R.string.ActionBotDocumentRentalAgreement);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
    return LocaleController.getString("ActionBotDocumentInternalPassport",R.string.ActionBotDocumentInternalPassport);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypePassportRegistration) {
    return LocaleController.getString("ActionBotDocumentPassportRegistration",R.string.ActionBotDocumentPassportRegistration);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) {
    return LocaleController.getString("ActionBotDocumentTemporaryRegistration",R.string.ActionBotDocumentTemporaryRegistration);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypePhone) {
    return LocaleController.getString("ActionBotDocumentPhone",R.string.ActionBotDocumentPhone);
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeEmail) {
    return LocaleController.getString("ActionBotDocumentEmail",R.string.ActionBotDocumentEmail);
  }
  return "";
}

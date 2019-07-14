private TLRPC.TL_secureValue getValueByType(TLRPC.TL_secureRequiredType requiredType,boolean check){
  if (requiredType == null) {
    return null;
  }
  for (int a=0, size=currentForm.values.size(); a < size; a++) {
    TLRPC.TL_secureValue secureValue=currentForm.values.get(a);
    if (requiredType.type.getClass() == secureValue.type.getClass()) {
      if (check) {
        if (requiredType.selfie_required) {
          if (!(secureValue.selfie instanceof TLRPC.TL_secureFile)) {
            return null;
          }
        }
        if (requiredType.translation_required) {
          if (secureValue.translation.isEmpty()) {
            return null;
          }
        }
        if (isAddressDocument(requiredType.type)) {
          if (secureValue.files.isEmpty()) {
            return null;
          }
        }
        if (isPersonalDocument(requiredType.type)) {
          if (!(secureValue.front_side instanceof TLRPC.TL_secureFile)) {
            return null;
          }
        }
        if (requiredType.type instanceof TLRPC.TL_secureValueTypeDriverLicense || requiredType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
          if (!(secureValue.reverse_side instanceof TLRPC.TL_secureFile)) {
            return null;
          }
        }
        if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails || requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
          String[] keys;
          if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
            if (requiredType.native_names) {
              keys=new String[]{"first_name_native","last_name_native","birth_date","gender","country_code","residence_country_code"};
            }
 else {
              keys=new String[]{"first_name","last_name","birth_date","gender","country_code","residence_country_code"};
            }
          }
 else {
            keys=new String[]{"street_line1","street_line2","post_code","city","state","country_code"};
          }
          try {
            JSONObject jsonObject=new JSONObject(decryptData(secureValue.data.data,decryptValueSecret(secureValue.data.secret,secureValue.data.data_hash),secureValue.data.data_hash));
            for (int b=0; b < keys.length; b++) {
              if (!jsonObject.has(keys[b]) || TextUtils.isEmpty(jsonObject.getString(keys[b]))) {
                return null;
              }
            }
          }
 catch (          Throwable ignore) {
            return null;
          }
        }
      }
      return secureValue;
    }
  }
  return null;
}

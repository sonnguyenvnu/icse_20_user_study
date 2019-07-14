private void setTypeValue(TLRPC.TL_secureRequiredType requiredType,String text,String json,TLRPC.TL_secureRequiredType documentRequiredType,String documentsJson,boolean documentOnly,int availableDocumentTypesCount){
  TextDetailSecureCell view=typesViews.get(requiredType);
  if (view == null) {
    if (currentActivityType == TYPE_MANAGE) {
      ArrayList<TLRPC.TL_secureRequiredType> documentTypes=new ArrayList<>();
      if (documentRequiredType != null) {
        documentTypes.add(documentRequiredType);
      }
      View prev=linearLayout2.getChildAt(linearLayout2.getChildCount() - 6);
      if (prev instanceof TextDetailSecureCell) {
        ((TextDetailSecureCell)prev).setNeedDivider(true);
      }
      view=addField(getParentActivity(),requiredType,documentTypes,true,true);
      updateManageVisibility();
    }
 else {
      return;
    }
  }
  HashMap<String,String> values=typesValues.get(requiredType);
  HashMap<String,String> documentValues=documentRequiredType != null ? typesValues.get(documentRequiredType) : null;
  TLRPC.TL_secureValue requiredTypeValue=getValueByType(requiredType,true);
  TLRPC.TL_secureValue documentRequiredTypeValue=getValueByType(documentRequiredType,true);
  if (json != null && languageMap == null) {
    languageMap=new HashMap<>();
    try {
      BufferedReader reader=new BufferedReader(new InputStreamReader(ApplicationLoader.applicationContext.getResources().getAssets().open("countries.txt")));
      String line;
      while ((line=reader.readLine()) != null) {
        String[] args=line.split(";");
        languageMap.put(args[1],args[2]);
      }
      reader.close();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else {
    languageMap=null;
  }
  String value=null;
  if (text != null) {
    if (requiredType.type instanceof TLRPC.TL_secureValueTypePhone) {
      value=PhoneFormat.getInstance().format("+" + text);
    }
 else     if (requiredType.type instanceof TLRPC.TL_secureValueTypeEmail) {
      value=text;
    }
  }
 else {
    StringBuilder stringBuilder=null;
    if (currentActivityType != TYPE_MANAGE && documentRequiredType != null && (!TextUtils.isEmpty(documentsJson) || documentRequiredTypeValue != null)) {
      if (stringBuilder == null) {
        stringBuilder=new StringBuilder();
      }
      if (availableDocumentTypesCount > 1) {
        stringBuilder.append(getTextForType(documentRequiredType.type));
      }
 else       if (TextUtils.isEmpty(documentsJson)) {
        stringBuilder.append(LocaleController.getString("PassportDocuments",R.string.PassportDocuments));
      }
    }
    if (json != null || documentsJson != null) {
      if (values == null) {
        return;
      }
      values.clear();
      String[] keys=null;
      String[] documentKeys=null;
      if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
        if (currentActivityType == TYPE_REQUEST && !documentOnly || currentActivityType == TYPE_MANAGE && documentRequiredType == null) {
          keys=new String[]{"first_name","middle_name","last_name","first_name_native","middle_name_native","last_name_native","birth_date","gender","country_code","residence_country_code"};
        }
        if (currentActivityType == TYPE_REQUEST || currentActivityType == TYPE_MANAGE && documentRequiredType != null) {
          documentKeys=new String[]{"document_no","expiry_date"};
        }
      }
 else       if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
        if (currentActivityType == TYPE_REQUEST && !documentOnly || currentActivityType == TYPE_MANAGE && documentRequiredType == null) {
          keys=new String[]{"street_line1","street_line2","post_code","city","state","country_code"};
        }
      }
      if (keys != null || documentKeys != null) {
        try {
          JSONObject jsonObject=null;
          String[] currentKeys=null;
          for (int b=0; b < 2; b++) {
            if (b == 0) {
              if (json != null) {
                jsonObject=new JSONObject(json);
                currentKeys=keys;
              }
            }
 else {
              if (documentValues == null) {
                continue;
              }
              if (documentsJson != null) {
                jsonObject=new JSONObject(documentsJson);
                currentKeys=documentKeys;
              }
            }
            if (currentKeys == null || jsonObject == null) {
              continue;
            }
            try {
              Iterator<String> iter=jsonObject.keys();
              while (iter.hasNext()) {
                String key=iter.next();
                if (b == 0) {
                  values.put(key,jsonObject.getString(key));
                }
 else {
                  documentValues.put(key,jsonObject.getString(key));
                }
              }
            }
 catch (            Throwable e) {
              FileLog.e(e);
            }
            for (int a=0; a < currentKeys.length; a++) {
              if (jsonObject.has(currentKeys[a])) {
                if (stringBuilder == null) {
                  stringBuilder=new StringBuilder();
                }
                String jsonValue=jsonObject.getString(currentKeys[a]);
                if (jsonValue != null) {
                  if (!TextUtils.isEmpty(jsonValue)) {
                    if ("first_name_native".equals(currentKeys[a]) || "middle_name_native".equals(currentKeys[a]) || "last_name_native".equals(currentKeys[a])) {
                      continue;
                    }
                    if (stringBuilder.length() > 0) {
                      if ("last_name".equals(currentKeys[a]) || "last_name_native".equals(currentKeys[a]) || "middle_name".equals(currentKeys[a]) || "middle_name_native".equals(currentKeys[a])) {
                        stringBuilder.append(" ");
                      }
 else {
                        stringBuilder.append(", ");
                      }
                    }
switch (currentKeys[a]) {
case "country_code":
case "residence_country_code":
                      String country=languageMap.get(jsonValue);
                    if (country != null) {
                      stringBuilder.append(country);
                    }
                  break;
case "gender":
                if ("male".equals(jsonValue)) {
                  stringBuilder.append(LocaleController.getString("PassportMale",R.string.PassportMale));
                }
 else                 if ("female".equals(jsonValue)) {
                  stringBuilder.append(LocaleController.getString("PassportFemale",R.string.PassportFemale));
                }
              break;
default :
            stringBuilder.append(jsonValue);
          break;
      }
    }
  }
}
}
}
}
 catch (Exception ignore) {
}
}
}
if (stringBuilder != null) {
value=stringBuilder.toString();
}
}
boolean isError=false;
HashMap<String,String> errors=!documentOnly ? errorsMap.get(getNameForType(requiredType.type)) : null;
HashMap<String,String> documentsErrors=documentRequiredType != null ? errorsMap.get(getNameForType(documentRequiredType.type)) : null;
if (errors != null && errors.size() > 0 || documentsErrors != null && documentsErrors.size() > 0) {
value=null;
if (!documentOnly) {
value=mainErrorsMap.get(getNameForType(requiredType.type));
}
if (value == null) {
value=mainErrorsMap.get(getNameForType(documentRequiredType.type));
}
isError=true;
}
 else {
if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
if (TextUtils.isEmpty(value)) {
if (documentRequiredType == null) {
value=LocaleController.getString("PassportPersonalDetailsInfo",R.string.PassportPersonalDetailsInfo);
}
 else {
if (currentActivityType == TYPE_MANAGE) {
value=LocaleController.getString("PassportDocuments",R.string.PassportDocuments);
}
 else {
if (availableDocumentTypesCount == 1) {
if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypePassport) {
  value=LocaleController.getString("PassportIdentityPassport",R.string.PassportIdentityPassport);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
  value=LocaleController.getString("PassportIdentityInternalPassport",R.string.PassportIdentityInternalPassport);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
  value=LocaleController.getString("PassportIdentityDriverLicence",R.string.PassportIdentityDriverLicence);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
  value=LocaleController.getString("PassportIdentityID",R.string.PassportIdentityID);
}
}
 else {
value=LocaleController.getString("PassportIdentityDocumentInfo",R.string.PassportIdentityDocumentInfo);
}
}
}
}
}
 else if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
if (TextUtils.isEmpty(value)) {
if (documentRequiredType == null) {
value=LocaleController.getString("PassportAddressNoUploadInfo",R.string.PassportAddressNoUploadInfo);
}
 else {
if (currentActivityType == TYPE_MANAGE) {
value=LocaleController.getString("PassportDocuments",R.string.PassportDocuments);
}
 else {
if (availableDocumentTypesCount == 1) {
if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeRentalAgreement) {
  value=LocaleController.getString("PassportAddAgreementInfo",R.string.PassportAddAgreementInfo);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeUtilityBill) {
  value=LocaleController.getString("PassportAddBillInfo",R.string.PassportAddBillInfo);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypePassportRegistration) {
  value=LocaleController.getString("PassportAddPassportRegistrationInfo",R.string.PassportAddPassportRegistrationInfo);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) {
  value=LocaleController.getString("PassportAddTemporaryRegistrationInfo",R.string.PassportAddTemporaryRegistrationInfo);
}
 else if (documentRequiredType.type instanceof TLRPC.TL_secureValueTypeBankStatement) {
  value=LocaleController.getString("PassportAddBankInfo",R.string.PassportAddBankInfo);
}
}
 else {
value=LocaleController.getString("PassportAddressInfo",R.string.PassportAddressInfo);
}
}
}
}
}
 else if (requiredType.type instanceof TLRPC.TL_secureValueTypePhone) {
if (TextUtils.isEmpty(value)) {
value=LocaleController.getString("PassportPhoneInfo",R.string.PassportPhoneInfo);
}
}
 else if (requiredType.type instanceof TLRPC.TL_secureValueTypeEmail) {
if (TextUtils.isEmpty(value)) {
value=LocaleController.getString("PassportEmailInfo",R.string.PassportEmailInfo);
}
}
}
view.setValue(value);
view.valueTextView.setTextColor(Theme.getColor(isError ? Theme.key_windowBackgroundWhiteRedText3 : Theme.key_windowBackgroundWhiteGrayText2));
view.setChecked(!isError && currentActivityType != TYPE_MANAGE && (documentOnly && documentRequiredType != null || !documentOnly && requiredTypeValue != null) && (documentRequiredType == null || documentRequiredTypeValue != null));
}

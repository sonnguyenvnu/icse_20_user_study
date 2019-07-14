private TextDetailSecureCell addField(Context context,final TLRPC.TL_secureRequiredType requiredType,final ArrayList<TLRPC.TL_secureRequiredType> documentRequiredTypes,boolean documentOnly,boolean last){
  final int availableDocumentTypesCount=documentRequiredTypes != null ? documentRequiredTypes.size() : 0;
  TextDetailSecureCell view=new TextDetailSecureCell(context);
  view.setBackgroundDrawable(Theme.getSelectorDrawable(true));
  if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
    String text;
    if (documentRequiredTypes == null || documentRequiredTypes.isEmpty()) {
      text=LocaleController.getString("PassportPersonalDetails",R.string.PassportPersonalDetails);
    }
 else     if (documentOnly && documentRequiredTypes.size() == 1) {
      text=getTextForType(documentRequiredTypes.get(0).type);
    }
 else     if (documentOnly && documentRequiredTypes.size() == 2) {
      text=LocaleController.formatString("PassportTwoDocuments",R.string.PassportTwoDocuments,getTextForType(documentRequiredTypes.get(0).type),getTextForType(documentRequiredTypes.get(1).type));
    }
 else {
      text=LocaleController.getString("PassportIdentityDocument",R.string.PassportIdentityDocument);
    }
    view.setTextAndValue(text,"",!last);
  }
 else   if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
    String text;
    if (documentRequiredTypes == null || documentRequiredTypes.isEmpty()) {
      text=LocaleController.getString("PassportAddress",R.string.PassportAddress);
    }
 else     if (documentOnly && documentRequiredTypes.size() == 1) {
      text=getTextForType(documentRequiredTypes.get(0).type);
    }
 else     if (documentOnly && documentRequiredTypes.size() == 2) {
      text=LocaleController.formatString("PassportTwoDocuments",R.string.PassportTwoDocuments,getTextForType(documentRequiredTypes.get(0).type),getTextForType(documentRequiredTypes.get(1).type));
    }
 else {
      text=LocaleController.getString("PassportResidentialAddress",R.string.PassportResidentialAddress);
    }
    view.setTextAndValue(text,"",!last);
  }
 else   if (requiredType.type instanceof TLRPC.TL_secureValueTypePhone) {
    view.setTextAndValue(LocaleController.getString("PassportPhone",R.string.PassportPhone),"",!last);
  }
 else   if (requiredType.type instanceof TLRPC.TL_secureValueTypeEmail) {
    view.setTextAndValue(LocaleController.getString("PassportEmail",R.string.PassportEmail),"",!last);
  }
  if (currentActivityType == TYPE_MANAGE) {
    linearLayout2.addView(view,linearLayout2.getChildCount() - 5,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  }
 else {
    linearLayout2.addView(view,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  }
  view.setOnClickListener(v -> {
    TLRPC.TL_secureRequiredType documentsType=null;
    if (documentRequiredTypes != null) {
      for (int a=0, count=documentRequiredTypes.size(); a < count; a++) {
        TLRPC.TL_secureRequiredType documentType=documentRequiredTypes.get(a);
        if (getValueByType(documentType,false) != null || count == 1) {
          documentsType=documentType;
          break;
        }
      }
    }
    if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails || requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
      if (documentsType == null && documentRequiredTypes != null && !documentRequiredTypes.isEmpty()) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
          builder.setTitle(LocaleController.getString("PassportIdentityDocument",R.string.PassportIdentityDocument));
        }
 else         if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
          builder.setTitle(LocaleController.getString("PassportAddress",R.string.PassportAddress));
        }
        ArrayList<String> strings=new ArrayList<>();
        for (int a=0, count=documentRequiredTypes.size(); a < count; a++) {
          TLRPC.TL_secureRequiredType documentType=documentRequiredTypes.get(a);
          if (documentType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
            strings.add(LocaleController.getString("PassportAddLicence",R.string.PassportAddLicence));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypePassport) {
            strings.add(LocaleController.getString("PassportAddPassport",R.string.PassportAddPassport));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
            strings.add(LocaleController.getString("PassportAddInternalPassport",R.string.PassportAddInternalPassport));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
            strings.add(LocaleController.getString("PassportAddCard",R.string.PassportAddCard));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeUtilityBill) {
            strings.add(LocaleController.getString("PassportAddBill",R.string.PassportAddBill));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeBankStatement) {
            strings.add(LocaleController.getString("PassportAddBank",R.string.PassportAddBank));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeRentalAgreement) {
            strings.add(LocaleController.getString("PassportAddAgreement",R.string.PassportAddAgreement));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) {
            strings.add(LocaleController.getString("PassportAddTemporaryRegistration",R.string.PassportAddTemporaryRegistration));
          }
 else           if (documentType.type instanceof TLRPC.TL_secureValueTypePassportRegistration) {
            strings.add(LocaleController.getString("PassportAddPassportRegistration",R.string.PassportAddPassportRegistration));
          }
        }
        builder.setItems(strings.toArray(new CharSequence[0]),(dialog,which) -> openTypeActivity(requiredType,documentRequiredTypes.get(which),documentRequiredTypes,documentOnly));
        showDialog(builder.create());
        return;
      }
    }
 else {
      boolean phoneField;
      if ((phoneField=(requiredType.type instanceof TLRPC.TL_secureValueTypePhone)) || requiredType.type instanceof TLRPC.TL_secureValueTypeEmail) {
        final TLRPC.TL_secureValue value=getValueByType(requiredType,false);
        if (value != null) {
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
            needShowProgress();
            deleteValueInternal(requiredType,null,null,true,this::needHideProgress,(error,text) -> needHideProgress(),documentOnly);
          }
);
          builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder.setMessage(phoneField ? LocaleController.getString("PassportDeletePhoneAlert",R.string.PassportDeletePhoneAlert) : LocaleController.getString("PassportDeleteEmailAlert",R.string.PassportDeleteEmailAlert));
          showDialog(builder.create());
          return;
        }
      }
    }
    openTypeActivity(requiredType,documentsType,documentRequiredTypes,documentOnly);
  }
);
  typesViews.put(requiredType,view);
  String text=null;
  String json=null;
  String documentJson=null;
  typesValues.put(requiredType,new HashMap<>());
  TLRPC.TL_secureValue value=getValueByType(requiredType,false);
  if (value != null) {
    if (value.plain_data instanceof TLRPC.TL_securePlainEmail) {
      text=((TLRPC.TL_securePlainEmail)value.plain_data).email;
    }
 else     if (value.plain_data instanceof TLRPC.TL_securePlainPhone) {
      text=((TLRPC.TL_securePlainPhone)value.plain_data).phone;
    }
 else     if (value.data != null) {
      json=decryptData(value.data.data,decryptValueSecret(value.data.secret,value.data.data_hash),value.data.data_hash);
    }
  }
  TLRPC.TL_secureRequiredType documentsType=null;
  if (documentRequiredTypes != null && !documentRequiredTypes.isEmpty()) {
    boolean found=false;
    for (int a=0, count=documentRequiredTypes.size(); a < count; a++) {
      TLRPC.TL_secureRequiredType documentType=documentRequiredTypes.get(a);
      typesValues.put(documentType,new HashMap<>());
      documentsToTypesLink.put(documentType,requiredType);
      if (!found) {
        TLRPC.TL_secureValue documentValue=getValueByType(documentType,false);
        if (documentValue != null) {
          if (documentValue.data != null) {
            documentJson=decryptData(documentValue.data.data,decryptValueSecret(documentValue.data.secret,documentValue.data.data_hash),documentValue.data.data_hash);
          }
          documentsType=documentType;
          found=true;
        }
      }
    }
    if (documentsType == null) {
      documentsType=documentRequiredTypes.get(0);
    }
  }
  setTypeValue(requiredType,text,json,documentsType,documentJson,documentOnly,availableDocumentTypesCount);
  return view;
}

private void openTypeActivity(TLRPC.TL_secureRequiredType requiredType,TLRPC.TL_secureRequiredType documentRequiredType,ArrayList<TLRPC.TL_secureRequiredType> availableDocumentTypes,boolean documentOnly){
  int activityType=-1;
  final int availableDocumentTypesCount=availableDocumentTypes != null ? availableDocumentTypes.size() : 0;
  TLRPC.SecureValueType type=requiredType.type;
  TLRPC.SecureValueType documentType=documentRequiredType != null ? documentRequiredType.type : null;
  if (type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
    activityType=TYPE_IDENTITY;
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeAddress) {
    activityType=TYPE_ADDRESS;
  }
 else   if (type instanceof TLRPC.TL_secureValueTypePhone) {
    activityType=TYPE_PHONE;
  }
 else   if (type instanceof TLRPC.TL_secureValueTypeEmail) {
    activityType=TYPE_EMAIL;
  }
  if (activityType != -1) {
    HashMap<String,String> errors=!documentOnly ? errorsMap.get(getNameForType(type)) : null;
    HashMap<String,String> documentsErrors=errorsMap.get(getNameForType(documentType));
    TLRPC.TL_secureValue value=getValueByType(requiredType,false);
    TLRPC.TL_secureValue documentsValue=getValueByType(documentRequiredType,false);
    final PassportActivity activity=new PassportActivity(activityType,currentForm,currentPassword,requiredType,value,documentRequiredType,documentsValue,typesValues.get(requiredType),documentRequiredType != null ? typesValues.get(documentRequiredType) : null);
    activity.delegate=new PassportActivityDelegate(){
      private TLRPC.InputSecureFile getInputSecureFile(      SecureDocument document){
        if (document.inputFile != null) {
          TLRPC.TL_inputSecureFileUploaded inputSecureFileUploaded=new TLRPC.TL_inputSecureFileUploaded();
          inputSecureFileUploaded.id=document.inputFile.id;
          inputSecureFileUploaded.parts=document.inputFile.parts;
          inputSecureFileUploaded.md5_checksum=document.inputFile.md5_checksum;
          inputSecureFileUploaded.file_hash=document.fileHash;
          inputSecureFileUploaded.secret=document.fileSecret;
          return inputSecureFileUploaded;
        }
 else {
          TLRPC.TL_inputSecureFile inputSecureFile=new TLRPC.TL_inputSecureFile();
          inputSecureFile.id=document.secureFile.id;
          inputSecureFile.access_hash=document.secureFile.access_hash;
          return inputSecureFile;
        }
      }
      private void renameFile(      SecureDocument oldDocument,      TLRPC.TL_secureFile newSecureFile){
        File oldFile=FileLoader.getPathToAttach(oldDocument);
        String oldKey=oldDocument.secureFile.dc_id + "_" + oldDocument.secureFile.id;
        File newFile=FileLoader.getPathToAttach(newSecureFile);
        String newKey=newSecureFile.dc_id + "_" + newSecureFile.id;
        oldFile.renameTo(newFile);
        ImageLoader.getInstance().replaceImageInCache(oldKey,newKey,null,false);
      }
      @Override public void saveValue(      final TLRPC.TL_secureRequiredType requiredType,      final String text,      final String json,      final TLRPC.TL_secureRequiredType documentRequiredType,      final String documentsJson,      final ArrayList<SecureDocument> documents,      final SecureDocument selfie,      final ArrayList<SecureDocument> translationDocuments,      final SecureDocument front,      final SecureDocument reverse,      final Runnable finishRunnable,      final ErrorRunnable errorRunnable){
        TLRPC.TL_inputSecureValue inputSecureValue=null;
        if (!TextUtils.isEmpty(json)) {
          inputSecureValue=new TLRPC.TL_inputSecureValue();
          inputSecureValue.type=requiredType.type;
          inputSecureValue.flags|=1;
          EncryptionResult result=encryptData(AndroidUtilities.getStringBytes(json));
          inputSecureValue.data=new TLRPC.TL_secureData();
          inputSecureValue.data.data=result.encryptedData;
          inputSecureValue.data.data_hash=result.fileHash;
          inputSecureValue.data.secret=result.fileSecret;
        }
 else         if (!TextUtils.isEmpty(text)) {
          TLRPC.SecurePlainData plainData;
          if (type instanceof TLRPC.TL_secureValueTypeEmail) {
            TLRPC.TL_securePlainEmail securePlainEmail=new TLRPC.TL_securePlainEmail();
            securePlainEmail.email=text;
            plainData=securePlainEmail;
          }
 else           if (type instanceof TLRPC.TL_secureValueTypePhone) {
            TLRPC.TL_securePlainPhone securePlainPhone=new TLRPC.TL_securePlainPhone();
            securePlainPhone.phone=text;
            plainData=securePlainPhone;
          }
 else {
            return;
          }
          inputSecureValue=new TLRPC.TL_inputSecureValue();
          inputSecureValue.type=requiredType.type;
          inputSecureValue.flags|=32;
          inputSecureValue.plain_data=plainData;
        }
        if (!documentOnly && inputSecureValue == null) {
          if (errorRunnable != null) {
            errorRunnable.onError(null,null);
          }
          return;
        }
        TLRPC.TL_inputSecureValue fileInputSecureValue;
        if (documentRequiredType != null) {
          fileInputSecureValue=new TLRPC.TL_inputSecureValue();
          fileInputSecureValue.type=documentRequiredType.type;
          if (!TextUtils.isEmpty(documentsJson)) {
            fileInputSecureValue.flags|=1;
            EncryptionResult result=encryptData(AndroidUtilities.getStringBytes(documentsJson));
            fileInputSecureValue.data=new TLRPC.TL_secureData();
            fileInputSecureValue.data.data=result.encryptedData;
            fileInputSecureValue.data.data_hash=result.fileHash;
            fileInputSecureValue.data.secret=result.fileSecret;
          }
          if (front != null) {
            fileInputSecureValue.front_side=getInputSecureFile(front);
            fileInputSecureValue.flags|=2;
          }
          if (reverse != null) {
            fileInputSecureValue.reverse_side=getInputSecureFile(reverse);
            fileInputSecureValue.flags|=4;
          }
          if (selfie != null) {
            fileInputSecureValue.selfie=getInputSecureFile(selfie);
            fileInputSecureValue.flags|=8;
          }
          if (translationDocuments != null && !translationDocuments.isEmpty()) {
            fileInputSecureValue.flags|=64;
            for (int a=0, size=translationDocuments.size(); a < size; a++) {
              fileInputSecureValue.translation.add(getInputSecureFile(translationDocuments.get(a)));
            }
          }
          if (documents != null && !documents.isEmpty()) {
            fileInputSecureValue.flags|=16;
            for (int a=0, size=documents.size(); a < size; a++) {
              fileInputSecureValue.files.add(getInputSecureFile(documents.get(a)));
            }
          }
          if (documentOnly) {
            inputSecureValue=fileInputSecureValue;
            fileInputSecureValue=null;
          }
        }
 else {
          fileInputSecureValue=null;
        }
        final PassportActivityDelegate currentDelegate=this;
        final TLRPC.TL_inputSecureValue finalFileInputSecureValue=fileInputSecureValue;
        final TLRPC.TL_account_saveSecureValue req=new TLRPC.TL_account_saveSecureValue();
        req.value=inputSecureValue;
        req.secure_secret_id=secureSecretId;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
          private void onResult(          final TLRPC.TL_error error,          final TLRPC.TL_secureValue newValue,          final TLRPC.TL_secureValue newPendingValue){
            AndroidUtilities.runOnUIThread(() -> {
              if (error != null) {
                if (errorRunnable != null) {
                  errorRunnable.onError(error.text,text);
                }
                AlertsCreator.processError(currentAccount,error,PassportActivity.this,req,text);
              }
 else {
                if (documentOnly) {
                  if (documentRequiredType != null) {
                    removeValue(documentRequiredType);
                  }
 else {
                    removeValue(requiredType);
                  }
                }
 else {
                  removeValue(requiredType);
                  removeValue(documentRequiredType);
                }
                if (newValue != null) {
                  currentForm.values.add(newValue);
                }
                if (newPendingValue != null) {
                  currentForm.values.add(newPendingValue);
                }
                if (documents != null && !documents.isEmpty()) {
                  for (int a=0, size=documents.size(); a < size; a++) {
                    SecureDocument document=documents.get(a);
                    if (document.inputFile != null) {
                      for (int b=0, size2=newValue.files.size(); b < size2; b++) {
                        TLRPC.SecureFile file=newValue.files.get(b);
                        if (file instanceof TLRPC.TL_secureFile) {
                          TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)file;
                          if (Utilities.arraysEquals(document.fileSecret,0,secureFile.secret,0)) {
                            renameFile(document,secureFile);
                            break;
                          }
                        }
                      }
                    }
                  }
                }
                if (selfie != null && selfie.inputFile != null && newValue.selfie instanceof TLRPC.TL_secureFile) {
                  TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)newValue.selfie;
                  if (Utilities.arraysEquals(selfie.fileSecret,0,secureFile.secret,0)) {
                    renameFile(selfie,secureFile);
                  }
                }
                if (front != null && front.inputFile != null && newValue.front_side instanceof TLRPC.TL_secureFile) {
                  TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)newValue.front_side;
                  if (Utilities.arraysEquals(front.fileSecret,0,secureFile.secret,0)) {
                    renameFile(front,secureFile);
                  }
                }
                if (reverse != null && reverse.inputFile != null && newValue.reverse_side instanceof TLRPC.TL_secureFile) {
                  TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)newValue.reverse_side;
                  if (Utilities.arraysEquals(reverse.fileSecret,0,secureFile.secret,0)) {
                    renameFile(reverse,secureFile);
                  }
                }
                if (translationDocuments != null && !translationDocuments.isEmpty()) {
                  for (int a=0, size=translationDocuments.size(); a < size; a++) {
                    SecureDocument document=translationDocuments.get(a);
                    if (document.inputFile != null) {
                      for (int b=0, size2=newValue.translation.size(); b < size2; b++) {
                        TLRPC.SecureFile file=newValue.translation.get(b);
                        if (file instanceof TLRPC.TL_secureFile) {
                          TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)file;
                          if (Utilities.arraysEquals(document.fileSecret,0,secureFile.secret,0)) {
                            renameFile(document,secureFile);
                            break;
                          }
                        }
                      }
                    }
                  }
                }
                setTypeValue(requiredType,text,json,documentRequiredType,documentsJson,documentOnly,availableDocumentTypesCount);
                if (finishRunnable != null) {
                  finishRunnable.run();
                }
              }
            }
);
          }
          @Override public void run(          final TLObject response,          final TLRPC.TL_error error){
            if (error != null) {
              if (error.text.equals("EMAIL_VERIFICATION_NEEDED")) {
                TLRPC.TL_account_sendVerifyEmailCode req=new TLRPC.TL_account_sendVerifyEmailCode();
                req.email=text;
                ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response1,error1) -> AndroidUtilities.runOnUIThread(() -> {
                  if (response1 != null) {
                    TLRPC.TL_account_sentEmailCode res=(TLRPC.TL_account_sentEmailCode)response1;
                    HashMap<String,String> values=new HashMap<>();
                    values.put("email",text);
                    values.put("pattern",res.email_pattern);
                    PassportActivity activity1=new PassportActivity(TYPE_EMAIL_VERIFICATION,currentForm,currentPassword,requiredType,null,null,null,values,null);
                    activity1.currentAccount=currentAccount;
                    activity1.emailCodeLength=res.length;
                    activity1.saltedPassword=saltedPassword;
                    activity1.secureSecret=secureSecret;
                    activity1.delegate=currentDelegate;
                    presentFragment(activity1,true);
                  }
 else {
                    showAlertWithText(LocaleController.getString("PassportEmail",R.string.PassportEmail),error1.text);
                    if (errorRunnable != null) {
                      errorRunnable.onError(error1.text,text);
                    }
                  }
                }
));
                return;
              }
 else               if (error.text.equals("PHONE_VERIFICATION_NEEDED")) {
                AndroidUtilities.runOnUIThread(() -> errorRunnable.onError(error.text,text));
                return;
              }
            }
            if (error == null && finalFileInputSecureValue != null) {
              final TLRPC.TL_secureValue pendingValue=(TLRPC.TL_secureValue)response;
              final TLRPC.TL_account_saveSecureValue req=new TLRPC.TL_account_saveSecureValue();
              req.value=finalFileInputSecureValue;
              req.secure_secret_id=secureSecretId;
              ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response12,error12) -> onResult(error12,(TLRPC.TL_secureValue)response12,pendingValue));
            }
 else {
              onResult(error,(TLRPC.TL_secureValue)response,null);
            }
          }
        }
);
      }
      @Override public SecureDocument saveFile(      TLRPC.TL_secureFile secureFile){
        String path=FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE) + "/" + secureFile.dc_id + "_" + secureFile.id + ".jpg";
        EncryptionResult result=createSecureDocument(path);
        return new SecureDocument(result.secureDocumentKey,secureFile,path,result.fileHash,result.fileSecret);
      }
      @Override public void deleteValue(      TLRPC.TL_secureRequiredType requiredType,      TLRPC.TL_secureRequiredType documentRequiredType,      ArrayList<TLRPC.TL_secureRequiredType> documentRequiredTypes,      boolean deleteType,      Runnable finishRunnable,      ErrorRunnable errorRunnable){
        deleteValueInternal(requiredType,documentRequiredType,documentRequiredTypes,deleteType,finishRunnable,errorRunnable,documentOnly);
      }
    }
;
    activity.currentAccount=currentAccount;
    activity.saltedPassword=saltedPassword;
    activity.secureSecret=secureSecret;
    activity.currentBotId=currentBotId;
    activity.fieldsErrors=errors;
    activity.documentOnly=documentOnly;
    activity.documentsErrors=documentsErrors;
    activity.availableDocumentTypes=availableDocumentTypes;
    if (activityType == TYPE_EMAIL) {
      activity.currentEmail=currentEmail;
    }
    presentFragment(activity);
  }
}

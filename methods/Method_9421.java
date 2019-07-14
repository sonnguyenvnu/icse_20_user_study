private void processSelectedFiles(final ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
  if (photos.isEmpty()) {
    return;
  }
  final boolean needRecoginze;
  if (uploadingFileType == UPLOADING_TYPE_SELFIE || uploadingFileType == UPLOADING_TYPE_TRANSLATION) {
    needRecoginze=false;
  }
 else   if (currentType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
    boolean allFieldsAreEmpty=true;
    for (int a=0; a < inputFields.length; a++) {
      if (a == FIELD_CITIZENSHIP || a == FIELD_EXPIRE || a == FIELD_GENDER || a == FIELD_RESIDENCE) {
        continue;
      }
      if (inputFields[a].length() > 0) {
        allFieldsAreEmpty=false;
        break;
      }
    }
    needRecoginze=allFieldsAreEmpty;
  }
 else {
    needRecoginze=false;
  }
  final int type=uploadingFileType;
  Utilities.globalQueue.postRunnable(() -> {
    boolean didRecognizeSuccessfully=false;
    for (int a=0, count=Math.min(uploadingFileType == UPLOADING_TYPE_DOCUMENTS || uploadingFileType == UPLOADING_TYPE_TRANSLATION ? 20 : 1,photos.size()); a < count; a++) {
      SendMessagesHelper.SendingMediaInfo info=photos.get(a);
      Bitmap bitmap=ImageLoader.loadBitmap(info.path,info.uri,2048,2048,false);
      if (bitmap == null) {
        continue;
      }
      TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(bitmap,2048,2048,89,false,320,320);
      if (size == null) {
        continue;
      }
      TLRPC.TL_secureFile secureFile=new TLRPC.TL_secureFile();
      secureFile.dc_id=(int)size.location.volume_id;
      secureFile.id=size.location.local_id;
      secureFile.date=(int)(System.currentTimeMillis() / 1000);
      final SecureDocument document=delegate.saveFile(secureFile);
      document.type=type;
      AndroidUtilities.runOnUIThread(() -> {
        if (uploadingFileType == UPLOADING_TYPE_SELFIE) {
          if (selfieDocument != null) {
            SecureDocumentCell cell=documentsCells.remove(selfieDocument);
            if (cell != null) {
              selfieLayout.removeView(cell);
            }
            selfieDocument=null;
          }
        }
 else         if (uploadingFileType == UPLOADING_TYPE_TRANSLATION) {
          if (translationDocuments.size() >= 20) {
            return;
          }
        }
 else         if (uploadingFileType == UPLOADING_TYPE_FRONT) {
          if (frontDocument != null) {
            SecureDocumentCell cell=documentsCells.remove(frontDocument);
            if (cell != null) {
              frontLayout.removeView(cell);
            }
            frontDocument=null;
          }
        }
 else         if (uploadingFileType == UPLOADING_TYPE_REVERSE) {
          if (reverseDocument != null) {
            SecureDocumentCell cell=documentsCells.remove(reverseDocument);
            if (cell != null) {
              reverseLayout.removeView(cell);
            }
            reverseDocument=null;
          }
        }
 else         if (uploadingFileType == UPLOADING_TYPE_DOCUMENTS) {
          if (documents.size() >= 20) {
            return;
          }
        }
        uploadingDocuments.put(document.path,document);
        doneItem.setEnabled(false);
        doneItem.setAlpha(0.5f);
        FileLoader.getInstance(currentAccount).uploadFile(document.path,false,true,ConnectionsManager.FileTypePhoto);
        addDocumentView(document,type);
        updateUploadText(type);
      }
);
      if (needRecoginze && !didRecognizeSuccessfully) {
        try {
          final MrzRecognizer.Result result=MrzRecognizer.recognize(bitmap,currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense);
          if (result != null) {
            didRecognizeSuccessfully=true;
            AndroidUtilities.runOnUIThread(() -> {
              if (result.type == MrzRecognizer.Result.TYPE_ID) {
                if (!(currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard)) {
                  for (int a1=0, count1=availableDocumentTypes.size(); a1 < count1; a1++) {
                    TLRPC.TL_secureRequiredType requiredType=availableDocumentTypes.get(a1);
                    if (requiredType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
                      currentDocumentsType=requiredType;
                      updateInterfaceStringsForDocumentType();
                      break;
                    }
                  }
                }
              }
 else               if (result.type == MrzRecognizer.Result.TYPE_PASSPORT) {
                if (!(currentDocumentsType.type instanceof TLRPC.TL_secureValueTypePassport)) {
                  for (int a1=0, count1=availableDocumentTypes.size(); a1 < count1; a1++) {
                    TLRPC.TL_secureRequiredType requiredType=availableDocumentTypes.get(a1);
                    if (requiredType.type instanceof TLRPC.TL_secureValueTypePassport) {
                      currentDocumentsType=requiredType;
                      updateInterfaceStringsForDocumentType();
                      break;
                    }
                  }
                }
              }
 else               if (result.type == MrzRecognizer.Result.TYPE_INTERNAL_PASSPORT) {
                if (!(currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeInternalPassport)) {
                  for (int a1=0, count1=availableDocumentTypes.size(); a1 < count1; a1++) {
                    TLRPC.TL_secureRequiredType requiredType=availableDocumentTypes.get(a1);
                    if (requiredType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
                      currentDocumentsType=requiredType;
                      updateInterfaceStringsForDocumentType();
                      break;
                    }
                  }
                }
              }
 else               if (result.type == MrzRecognizer.Result.TYPE_DRIVER_LICENSE) {
                if (!(currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense)) {
                  for (int a1=0, count1=availableDocumentTypes.size(); a1 < count1; a1++) {
                    TLRPC.TL_secureRequiredType requiredType=availableDocumentTypes.get(a1);
                    if (requiredType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
                      currentDocumentsType=requiredType;
                      updateInterfaceStringsForDocumentType();
                      break;
                    }
                  }
                }
              }
              if (!TextUtils.isEmpty(result.firstName)) {
                inputFields[FIELD_NAME].setText(result.firstName);
              }
              if (!TextUtils.isEmpty(result.middleName)) {
                inputFields[FIELD_MIDNAME].setText(result.middleName);
              }
              if (!TextUtils.isEmpty(result.lastName)) {
                inputFields[FIELD_SURNAME].setText(result.lastName);
              }
              if (!TextUtils.isEmpty(result.number)) {
                inputFields[FIELD_CARDNUMBER].setText(result.number);
              }
              if (result.gender != MrzRecognizer.Result.GENDER_UNKNOWN) {
switch (result.gender) {
case MrzRecognizer.Result.GENDER_MALE:
                  currentGender="male";
                inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportMale",R.string.PassportMale));
              break;
case MrzRecognizer.Result.GENDER_FEMALE:
            currentGender="female";
          inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportFemale",R.string.PassportFemale));
        break;
    }
  }
  if (!TextUtils.isEmpty(result.nationality)) {
    currentCitizeship=result.nationality;
    String country=languageMap.get(currentCitizeship);
    if (country != null) {
      inputFields[FIELD_CITIZENSHIP].setText(country);
    }
  }
  if (!TextUtils.isEmpty(result.issuingCountry)) {
    currentResidence=result.issuingCountry;
    String country=languageMap.get(currentResidence);
    if (country != null) {
      inputFields[FIELD_RESIDENCE].setText(country);
    }
  }
  if (result.birthDay > 0 && result.birthMonth > 0 && result.birthYear > 0) {
    inputFields[FIELD_BIRTHDAY].setText(String.format(Locale.US,"%02d.%02d.%d",result.birthDay,result.birthMonth,result.birthYear));
  }
  if (result.expiryDay > 0 && result.expiryMonth > 0 && result.expiryYear > 0) {
    currentExpireDate[0]=result.expiryYear;
    currentExpireDate[1]=result.expiryMonth;
    currentExpireDate[2]=result.expiryDay;
    inputFields[FIELD_EXPIRE].setText(String.format(Locale.US,"%02d.%02d.%d",result.expiryDay,result.expiryMonth,result.expiryYear));
  }
 else {
    currentExpireDate[0]=currentExpireDate[1]=currentExpireDate[2]=0;
    inputFields[FIELD_EXPIRE].setText(LocaleController.getString("PassportNoExpireDate",R.string.PassportNoExpireDate));
  }
}
);
}
}
 catch (Throwable e) {
FileLog.e(e);
}
}
}
SharedConfig.saveConfig();
}
);
}

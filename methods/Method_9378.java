private boolean checkFieldsForError(){
  if (currentDocumentsType != null) {
    if (errorsValues.containsKey("error_all") || errorsValues.containsKey("error_document_all")) {
      onFieldError(topErrorCell);
      return true;
    }
    if (uploadDocumentCell != null) {
      if (documents.isEmpty()) {
        onFieldError(uploadDocumentCell);
        return true;
      }
 else {
        for (int a=0, size=documents.size(); a < size; a++) {
          SecureDocument document=documents.get(a);
          String key="files" + getDocumentHash(document);
          if (key != null && errorsValues.containsKey(key)) {
            onFieldError(documentsCells.get(document));
            return true;
          }
        }
      }
    }
    if (errorsValues.containsKey("files_all") || errorsValues.containsKey("translation_all")) {
      onFieldError(bottomCell);
      return true;
    }
    if (uploadFrontCell != null) {
      if (frontDocument == null) {
        onFieldError(uploadFrontCell);
        return true;
      }
 else {
        String key="front" + getDocumentHash(frontDocument);
        if (errorsValues.containsKey(key)) {
          onFieldError(documentsCells.get(frontDocument));
          return true;
        }
      }
    }
    if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
      if (uploadReverseCell != null) {
        if (reverseDocument == null) {
          onFieldError(uploadReverseCell);
          return true;
        }
 else {
          String key="reverse" + getDocumentHash(reverseDocument);
          if (errorsValues.containsKey(key)) {
            onFieldError(documentsCells.get(reverseDocument));
            return true;
          }
        }
      }
    }
    if (uploadSelfieCell != null && currentBotId != 0) {
      if (selfieDocument == null) {
        onFieldError(uploadSelfieCell);
        return true;
      }
 else {
        String key="selfie" + getDocumentHash(selfieDocument);
        if (errorsValues.containsKey(key)) {
          onFieldError(documentsCells.get(selfieDocument));
          return true;
        }
      }
    }
    if (uploadTranslationCell != null && currentBotId != 0) {
      if (translationDocuments.isEmpty()) {
        onFieldError(uploadTranslationCell);
        return true;
      }
 else {
        for (int a=0, size=translationDocuments.size(); a < size; a++) {
          SecureDocument document=translationDocuments.get(a);
          String key="translation" + getDocumentHash(document);
          if (key != null && errorsValues.containsKey(key)) {
            onFieldError(documentsCells.get(document));
            return true;
          }
        }
      }
    }
  }
  for (int i=0; i < 2; i++) {
    EditTextBoldCursor[] fields;
    if (i == 0) {
      fields=inputFields;
    }
 else {
      fields=nativeInfoCell != null && nativeInfoCell.getVisibility() == View.VISIBLE ? inputExtraFields : null;
    }
    if (fields == null) {
      continue;
    }
    for (int a=0; a < fields.length; a++) {
      boolean error=false;
      if (fields[a].hasErrorText()) {
        error=true;
      }
      if (!errorsValues.isEmpty()) {
        String key;
        if (currentType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
          if (i == 0) {
switch (a) {
case FIELD_NAME:
              key="first_name";
            break;
case FIELD_MIDNAME:
          key="middle_name";
        break;
case FIELD_SURNAME:
      key="last_name";
    break;
case FIELD_BIRTHDAY:
  key="birth_date";
break;
case FIELD_GENDER:
key="gender";
break;
case FIELD_CITIZENSHIP:
key="country_code";
break;
case FIELD_RESIDENCE:
key="residence_country_code";
break;
case FIELD_CARDNUMBER:
key="document_no";
break;
case FIELD_EXPIRE:
key="expiry_date";
break;
default :
key=null;
break;
}
}
 else {
switch (a) {
case FIELD_NATIVE_NAME:
key="first_name_native";
break;
case FIELD_NATIVE_MIDNAME:
key="middle_name_native";
break;
case FIELD_NATIVE_SURNAME:
key="last_name_native";
break;
default :
key=null;
break;
}
}
}
 else if (currentType.type instanceof TLRPC.TL_secureValueTypeAddress) {
switch (a) {
case FIELD_STREET1:
key="street_line1";
break;
case FIELD_STREET2:
key="street_line2";
break;
case FIELD_CITY:
key="city";
break;
case FIELD_STATE:
key="state";
break;
case FIELD_COUNTRY:
key="country_code";
break;
case FIELD_POSTCODE:
key="post_code";
break;
default :
key=null;
break;
}
}
 else {
key=null;
}
if (key != null) {
String value=errorsValues.get(key);
if (!TextUtils.isEmpty(value)) {
if (value.equals(fields[a].getText().toString())) {
error=true;
}
}
}
}
if (documentOnly) {
if (currentDocumentsType != null && a < FIELD_CARDNUMBER) {
continue;
}
}
if (!error) {
int len=fields[a].length();
boolean allowZeroLength=false;
if (currentActivityType == TYPE_IDENTITY) {
if (a == FIELD_EXPIRE) {
continue;
}
 else if (i == 0 && (a == FIELD_NAME || a == FIELD_SURNAME || a == FIELD_MIDNAME) || i == 1 && (a == FIELD_NATIVE_NAME || a == FIELD_NATIVE_MIDNAME || a == FIELD_NATIVE_SURNAME)) {
if (len > 255) {
error=true;
}
if (i == 0 && a == FIELD_MIDNAME || i == 1 && a == FIELD_NATIVE_MIDNAME) {
allowZeroLength=true;
}
}
 else if (a == FIELD_CARDNUMBER) {
if (len > 24) {
error=true;
}
}
}
 else if (currentActivityType == TYPE_ADDRESS) {
if (a == FIELD_STREET2) {
continue;
}
 else if (a == FIELD_CITY) {
if (len < 2) {
error=true;
}
}
 else if (a == FIELD_STATE) {
if ("US".equals(currentCitizeship)) {
if (len < 2) {
error=true;
}
}
 else {
continue;
}
}
 else if (a == FIELD_POSTCODE) {
if (len < 2 || len > 10) {
error=true;
}
}
}
if (!error && !allowZeroLength && len == 0) {
error=true;
}
}
if (error) {
onFieldError(fields[a]);
return true;
}
}
}
return false;
}

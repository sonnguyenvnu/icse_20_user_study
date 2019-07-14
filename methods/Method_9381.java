private void updateUploadText(int type){
  if (type == UPLOADING_TYPE_DOCUMENTS) {
    if (uploadDocumentCell == null) {
      return;
    }
    if (documents.size() >= 1) {
      uploadDocumentCell.setText(LocaleController.getString("PassportUploadAdditinalDocument",R.string.PassportUploadAdditinalDocument),false);
    }
 else {
      uploadDocumentCell.setText(LocaleController.getString("PassportUploadDocument",R.string.PassportUploadDocument),false);
    }
  }
 else   if (type == UPLOADING_TYPE_SELFIE) {
    if (uploadSelfieCell == null) {
      return;
    }
    uploadSelfieCell.setVisibility(selfieDocument != null ? View.GONE : View.VISIBLE);
  }
 else   if (type == UPLOADING_TYPE_TRANSLATION) {
    if (uploadTranslationCell == null) {
      return;
    }
    if (translationDocuments.size() >= 1) {
      uploadTranslationCell.setText(LocaleController.getString("PassportUploadAdditinalDocument",R.string.PassportUploadAdditinalDocument),false);
    }
 else {
      uploadTranslationCell.setText(LocaleController.getString("PassportUploadDocument",R.string.PassportUploadDocument),false);
    }
  }
 else   if (type == UPLOADING_TYPE_FRONT) {
    if (uploadFrontCell == null) {
      return;
    }
    boolean divider=currentDocumentsType != null && (currentDocumentsType.selfie_required || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense);
    if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypePassport || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
      uploadFrontCell.setTextAndValue(LocaleController.getString("PassportMainPage",R.string.PassportMainPage),LocaleController.getString("PassportMainPageInfo",R.string.PassportMainPageInfo),divider);
    }
 else {
      uploadFrontCell.setTextAndValue(LocaleController.getString("PassportFrontSide",R.string.PassportFrontSide),LocaleController.getString("PassportFrontSideInfo",R.string.PassportFrontSideInfo),divider);
    }
    uploadFrontCell.setVisibility(frontDocument != null ? View.GONE : View.VISIBLE);
  }
 else   if (type == UPLOADING_TYPE_REVERSE) {
    if (uploadReverseCell == null) {
      return;
    }
    if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
      reverseLayout.setVisibility(View.VISIBLE);
      uploadReverseCell.setVisibility(reverseDocument != null ? View.GONE : View.VISIBLE);
    }
 else {
      reverseLayout.setVisibility(View.GONE);
      uploadReverseCell.setVisibility(View.GONE);
    }
  }
}

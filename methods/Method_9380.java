private void updateInterfaceStringsForDocumentType(){
  if (currentDocumentsType != null) {
    actionBar.setTitle(getTextForType(currentDocumentsType.type));
  }
 else {
    actionBar.setTitle(LocaleController.getString("PassportPersonal",R.string.PassportPersonal));
  }
  updateUploadText(UPLOADING_TYPE_FRONT);
  updateUploadText(UPLOADING_TYPE_REVERSE);
  updateUploadText(UPLOADING_TYPE_SELFIE);
  updateUploadText(UPLOADING_TYPE_TRANSLATION);
}

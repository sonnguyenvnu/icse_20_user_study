private void addDocumentView(final SecureDocument document,final int type){
  if (type == UPLOADING_TYPE_SELFIE) {
    selfieDocument=document;
    if (selfieLayout == null) {
      return;
    }
  }
 else   if (type == UPLOADING_TYPE_TRANSLATION) {
    translationDocuments.add(document);
    if (translationLayout == null) {
      return;
    }
  }
 else   if (type == UPLOADING_TYPE_FRONT) {
    frontDocument=document;
    if (frontLayout == null) {
      return;
    }
  }
 else   if (type == UPLOADING_TYPE_REVERSE) {
    reverseDocument=document;
    if (reverseLayout == null) {
      return;
    }
  }
 else {
    documents.add(document);
    if (documentsLayout == null) {
      return;
    }
  }
  if (getParentActivity() == null) {
    return;
  }
  final SecureDocumentCell cell=new SecureDocumentCell(getParentActivity());
  String value;
  final String key;
  cell.setTag(document);
  cell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
  String text;
  documentsCells.put(document,cell);
  String hash=getDocumentHash(document);
  if (type == UPLOADING_TYPE_SELFIE) {
    text=LocaleController.getString("PassportSelfie",R.string.PassportSelfie);
    selfieLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    key="selfie" + hash;
  }
 else   if (type == UPLOADING_TYPE_TRANSLATION) {
    text=LocaleController.getString("AttachPhoto",R.string.AttachPhoto);
    translationLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    key="translation" + hash;
  }
 else   if (type == UPLOADING_TYPE_FRONT) {
    if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypePassport || currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
      text=LocaleController.getString("PassportMainPage",R.string.PassportMainPage);
    }
 else {
      text=LocaleController.getString("PassportFrontSide",R.string.PassportFrontSide);
    }
    frontLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    key="front" + hash;
  }
 else   if (type == UPLOADING_TYPE_REVERSE) {
    text=LocaleController.getString("PassportReverseSide",R.string.PassportReverseSide);
    reverseLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    key="reverse" + hash;
  }
 else {
    text=LocaleController.getString("AttachPhoto",R.string.AttachPhoto);
    documentsLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    key="files" + hash;
  }
  if (key == null || documentsErrors == null || (value=documentsErrors.get(key)) == null) {
    value=LocaleController.formatDateForBan(document.secureFile.date);
  }
 else {
    cell.valueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText3));
    errorsValues.put(key,"");
  }
  cell.setTextAndValueAndImage(text,value,document);
  cell.setOnClickListener(v -> {
    uploadingFileType=type;
    if (type == UPLOADING_TYPE_SELFIE) {
      currentPhotoViewerLayout=selfieLayout;
    }
 else     if (type == UPLOADING_TYPE_TRANSLATION) {
      currentPhotoViewerLayout=translationLayout;
    }
 else     if (type == UPLOADING_TYPE_FRONT) {
      currentPhotoViewerLayout=frontLayout;
    }
 else     if (type == UPLOADING_TYPE_REVERSE) {
      currentPhotoViewerLayout=reverseLayout;
    }
 else {
      currentPhotoViewerLayout=documentsLayout;
    }
    SecureDocument document1=(SecureDocument)v.getTag();
    PhotoViewer.getInstance().setParentActivity(getParentActivity());
    if (type == UPLOADING_TYPE_DOCUMENTS) {
      PhotoViewer.getInstance().openPhoto(documents,documents.indexOf(document1),provider);
    }
 else {
      PhotoViewer.getInstance().openPhoto(translationDocuments,translationDocuments.indexOf(document1),provider);
    }
  }
);
  cell.setOnLongClickListener(v -> {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    if (type == UPLOADING_TYPE_SELFIE) {
      builder.setMessage(LocaleController.getString("PassportDeleteSelfie",R.string.PassportDeleteSelfie));
    }
 else {
      builder.setMessage(LocaleController.getString("PassportDeleteScan",R.string.PassportDeleteScan));
    }
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
      documentsCells.remove(document);
      if (type == UPLOADING_TYPE_SELFIE) {
        selfieDocument=null;
        selfieLayout.removeView(cell);
      }
 else       if (type == UPLOADING_TYPE_TRANSLATION) {
        translationDocuments.remove(document);
        translationLayout.removeView(cell);
      }
 else       if (type == UPLOADING_TYPE_FRONT) {
        frontDocument=null;
        frontLayout.removeView(cell);
      }
 else       if (type == UPLOADING_TYPE_REVERSE) {
        reverseDocument=null;
        reverseLayout.removeView(cell);
      }
 else {
        documents.remove(document);
        documentsLayout.removeView(cell);
      }
      if (key != null) {
        if (documentsErrors != null) {
          documentsErrors.remove(key);
        }
        if (errorsValues != null) {
          errorsValues.remove(key);
        }
      }
      updateUploadText(type);
      if (document.path != null && uploadingDocuments.remove(document.path) != null) {
        if (uploadingDocuments.isEmpty()) {
          doneItem.setEnabled(true);
          doneItem.setAlpha(1.0f);
        }
        FileLoader.getInstance(currentAccount).cancelUploadFile(document.path,false);
      }
    }
);
    showDialog(builder.create());
    return true;
  }
);
}

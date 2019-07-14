private int getMaxSelectedDocuments(){
  if (uploadingFileType == UPLOADING_TYPE_DOCUMENTS) {
    return 20 - documents.size();
  }
 else   if (uploadingFileType == UPLOADING_TYPE_TRANSLATION) {
    return 20 - translationDocuments.size();
  }
 else {
    return 1;
  }
}

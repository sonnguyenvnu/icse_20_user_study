void downloadLanguage(Language language){
  FirebaseTranslateRemoteModel model=getModel(FirebaseTranslateLanguage.languageForLanguageCode(language.getCode()));
  modelManager.downloadRemoteModelIfNeeded(model).addOnCompleteListener(new OnCompleteListener<Void>(){
    @Override public void onComplete(    @NonNull Task<Void> task){
      fetchDownloadedModels();
    }
  }
);
}

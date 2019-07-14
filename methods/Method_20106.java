public Task<String> translate(){
  final TaskCompletionSource<String> translateTask=new TaskCompletionSource<String>();
  final String text=sourceText.getValue();
  final Language source=sourceLang.getValue();
  final Language target=targetLang.getValue();
  if (source == null || target == null || text == null || text.isEmpty()) {
    return Tasks.forResult("");
  }
  int sourceLangCode=FirebaseTranslateLanguage.languageForLanguageCode(source.getCode());
  int targetLangCode=FirebaseTranslateLanguage.languageForLanguageCode(target.getCode());
  FirebaseTranslatorOptions options=new FirebaseTranslatorOptions.Builder().setSourceLanguage(sourceLangCode).setTargetLanguage(targetLangCode).build();
  final FirebaseTranslator translator=FirebaseNaturalLanguage.getInstance().getTranslator(options);
  return translator.downloadModelIfNeeded().continueWithTask(new Continuation<Void,Task<String>>(){
    @Override public Task<String> then(    @NonNull Task<Void> task){
      if (task.isSuccessful()) {
        return translator.translate(text);
      }
 else {
        Exception e=task.getException();
        if (e == null) {
          e=new Exception(getApplication().getString(R.string.unknown_error));
        }
        return Tasks.forException(e);
      }
    }
  }
);
}

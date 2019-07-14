private void identifyPossibleLanguages(final String inputText){
  FirebaseLanguageIdentification languageIdentification=FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
  languageIdentification.identifyPossibleLanguages(inputText).addOnSuccessListener(this,new OnSuccessListener<List<IdentifiedLanguage>>(){
    @Override public void onSuccess(    List<IdentifiedLanguage> identifiedLanguages){
      List<String> detectedLanguages=new ArrayList<>(identifiedLanguages.size());
      for (      IdentifiedLanguage language : identifiedLanguages) {
        detectedLanguages.add(String.format(Locale.US,"%s (%3f)",language.getLanguageCode(),language.getConfidence()));
      }
      outputText.append(String.format(Locale.US,"\n%s - [%s]",inputText,TextUtils.join(", ",detectedLanguages)));
    }
  }
).addOnFailureListener(this,new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      Log.e(TAG,"Language identification error",e);
      Toast.makeText(MainActivity.this,R.string.language_id_error,Toast.LENGTH_SHORT).show();
    }
  }
);
}

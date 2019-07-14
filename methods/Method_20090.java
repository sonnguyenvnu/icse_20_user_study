private void identifyLanguage(final String inputText){
  FirebaseLanguageIdentification languageIdentification=FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
  languageIdentification.identifyLanguage(inputText).addOnSuccessListener(this,new OnSuccessListener<String>(){
    @Override public void onSuccess(    String s){
      outputText.append(String.format(Locale.US,"\n%s - %s",inputText,s));
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

private void fetchDownloadedModels(){
  modelManager.getAvailableModels(FirebaseApp.getInstance()).addOnSuccessListener(new OnSuccessListener<Set<FirebaseTranslateRemoteModel>>(){
    @Override public void onSuccess(    Set<FirebaseTranslateRemoteModel> remoteModels){
      List<String> modelCodes=new ArrayList<>(remoteModels.size());
      for (      FirebaseTranslateRemoteModel model : remoteModels) {
        modelCodes.add(model.getLanguageCode());
      }
      Collections.sort(modelCodes);
      availableModels.setValue(modelCodes);
    }
  }
);
}

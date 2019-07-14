void switchUser(){
  clearSuggestions();
  emulatingRemoteUser.postValue(!emulatingRemoteUser.getValue());
}

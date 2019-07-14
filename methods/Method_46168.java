private void composeAndNotify(UserData userData,ConfigData configData){
  ComposeUserData mergedResult=composeUserAndConfigData(userData,configData);
  notifyToListener(mergedResult);
}

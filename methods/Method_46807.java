@Override public void onHistoryAdded(String path){
  utilsHandler.saveToDatabase(new OperationData(UtilsHandler.Operation.HISTORY,path));
}

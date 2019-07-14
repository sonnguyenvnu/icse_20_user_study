@Override public void onHiddenFileAdded(String path){
  utilsHandler.saveToDatabase(new OperationData(UtilsHandler.Operation.HIDDEN,path));
}

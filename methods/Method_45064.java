private void onLocalNavigationRequest(String uniqueStr){
  try {
    Selection selection=linkProvider.getDefinitionToSelectionMap().get(uniqueStr);
    doLocalNavigation(selection);
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}

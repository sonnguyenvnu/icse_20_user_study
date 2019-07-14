private boolean checkDataIdIsRefreshbable(String refreshDataIds,String sharedDataId){
  if (refreshDataIds == null || "".equals(refreshDataIds)) {
    return false;
  }
  String[] refreshDataIdArry=refreshDataIds.split(SHARED_CONFIG_SEPARATOR_CHAR);
  for (  String refreshDataId : refreshDataIdArry) {
    if (refreshDataId.equals(sharedDataId)) {
      return true;
    }
  }
  return false;
}

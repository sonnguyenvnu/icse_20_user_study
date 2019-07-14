@Override public boolean isAutoApprove(String scope){
  if (autoApproveScopes == null) {
    return false;
  }
  for (  String auto : autoApproveScopes) {
    if (auto.equals("true") || scope.matches(auto)) {
      return true;
    }
  }
  return false;
}

private void initializeCurrentMessages(Map<MessageScope,Integer> scopeMap){
  assert !scopeMap.isEmpty() && isValidIdMap(scopeMap);
  if (currentMessages == null) {
    if (scopeMap.size() > 1)     currentMessages=new Object[scopeMap.size()];
  }
}

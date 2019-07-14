private boolean hasNotValueForType(Class<? extends TLRPC.SecureValueType> type){
  for (int a=0, count=currentForm.values.size(); a < count; a++) {
    if (currentForm.values.get(a).type.getClass() == type) {
      return false;
    }
  }
  return true;
}

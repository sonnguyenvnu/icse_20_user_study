private TLRPC.TL_secureValue removeValue(TLRPC.TL_secureRequiredType requiredType){
  if (requiredType == null) {
    return null;
  }
  for (int a=0, size=currentForm.values.size(); a < size; a++) {
    TLRPC.TL_secureValue secureValue=currentForm.values.get(a);
    if (requiredType.type.getClass() == secureValue.type.getClass()) {
      return currentForm.values.remove(a);
    }
  }
  return null;
}

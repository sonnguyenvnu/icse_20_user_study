static public OnError stringToOnError(String s){
  if ("set-to-blank".equalsIgnoreCase(s)) {
    return OnError.SetToBlank;
  }
 else   if ("store-error".equalsIgnoreCase(s)) {
    return OnError.StoreError;
  }
 else {
    return OnError.KeepOriginal;
  }
}

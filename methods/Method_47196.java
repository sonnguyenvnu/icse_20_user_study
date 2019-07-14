private static void put(String mimeType,int resId){
  if (sMimeIconIds.put(mimeType,resId) != null) {
    throw new RuntimeException(mimeType + " already registered!");
  }
}

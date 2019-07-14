@MainThread @RequiresApi(Build.VERSION_CODES.M) @SuppressLint("PrivateApi") private static void flushThemedResourceCache(@NonNull Object themedResourceCache){
  if (!sThemedResourceCacheClassInitialized) {
    try {
      sThemedResourceCacheClass=Class.forName("android.content.res.ThemedResourceCache");
    }
 catch (    ClassNotFoundException e) {
      e.printStackTrace();
    }
    sThemedResourceCacheClassInitialized=true;
  }
  if (sThemedResourceCacheClass == null) {
    return;
  }
  if (!sThemedResourceCacheMThemedEntriesFieldInitialized) {
    try {
      sThemedResourceCacheMThemedEntriesField=sThemedResourceCacheClass.getDeclaredField("mThemedEntries");
      sThemedResourceCacheMThemedEntriesField.setAccessible(true);
    }
 catch (    NoSuchFieldException e) {
      e.printStackTrace();
    }
    sThemedResourceCacheMThemedEntriesFieldInitialized=true;
  }
  if (sThemedResourceCacheMThemedEntriesField != null) {
    ArrayMap themedEntries=null;
    try {
      themedEntries=(ArrayMap)sThemedResourceCacheMThemedEntriesField.get(themedResourceCache);
    }
 catch (    IllegalAccessException e) {
      e.printStackTrace();
    }
    if (themedEntries != null) {
      themedEntries.clear();
    }
  }
  if (!sThemedResourceCacheMUnthemedEntriesFieldInitialized) {
    try {
      sThemedResourceCacheMUnthemedEntriesField=sThemedResourceCacheClass.getDeclaredField("mUnthemedEntries");
      sThemedResourceCacheMUnthemedEntriesField.setAccessible(true);
    }
 catch (    NoSuchFieldException e) {
      e.printStackTrace();
    }
    sThemedResourceCacheMUnthemedEntriesFieldInitialized=true;
  }
  if (sThemedResourceCacheMUnthemedEntriesField != null) {
    LongSparseArray unthemedEntries=null;
    try {
      unthemedEntries=(LongSparseArray)sThemedResourceCacheMUnthemedEntriesField.get(themedResourceCache);
    }
 catch (    IllegalAccessException e) {
      e.printStackTrace();
    }
    if (unthemedEntries != null) {
      unthemedEntries.clear();
    }
  }
  if (!sThemedResourceCacheMNullThemedEntriesFieldInitialized) {
    try {
      sThemedResourceCacheMNullThemedEntriesField=sThemedResourceCacheClass.getDeclaredField("mNullThemedEntries");
      sThemedResourceCacheMNullThemedEntriesField.setAccessible(true);
    }
 catch (    NoSuchFieldException e) {
      e.printStackTrace();
    }
    sThemedResourceCacheMNullThemedEntriesFieldInitialized=true;
  }
  if (sThemedResourceCacheMNullThemedEntriesField != null) {
    LongSparseArray nullThemedEntries=null;
    try {
      nullThemedEntries=(LongSparseArray)sThemedResourceCacheMNullThemedEntriesField.get(themedResourceCache);
    }
 catch (    IllegalAccessException e) {
      e.printStackTrace();
    }
    if (nullThemedEntries != null) {
      nullThemedEntries.clear();
    }
  }
}

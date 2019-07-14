private void putBundleToEditor(Bundle bundle,SharedPreferences.Editor editor,String prefix){
  Set<String> keys=bundle.keySet();
  for (  String key : keys) {
    Object obj=bundle.get(key);
    if (obj instanceof String) {
      if (prefix != null) {
        editor.putString(prefix + "_|_" + key,(String)obj);
      }
 else {
        editor.putString(key,(String)obj);
      }
    }
 else     if (obj instanceof Integer) {
      if (prefix != null) {
        editor.putInt(prefix + "_|_" + key,(Integer)obj);
      }
 else {
        editor.putInt(key,(Integer)obj);
      }
    }
 else     if (obj instanceof Bundle) {
      putBundleToEditor((Bundle)obj,editor,key);
    }
  }
}

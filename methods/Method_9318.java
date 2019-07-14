private Bundle loadCurrentState(){
  if (newAccount) {
    return null;
  }
  try {
    Bundle bundle=new Bundle();
    SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("logininfo2",Context.MODE_PRIVATE);
    Map<String,?> params=preferences.getAll();
    for (    Map.Entry<String,?> entry : params.entrySet()) {
      String key=entry.getKey();
      Object value=entry.getValue();
      String[] args=key.split("_\\|_");
      if (args.length == 1) {
        if (value instanceof String) {
          bundle.putString(key,(String)value);
        }
 else         if (value instanceof Integer) {
          bundle.putInt(key,(Integer)value);
        }
      }
 else       if (args.length == 2) {
        Bundle inner=bundle.getBundle(args[0]);
        if (inner == null) {
          inner=new Bundle();
          bundle.putBundle(args[0],inner);
        }
        if (value instanceof String) {
          inner.putString(args[1],(String)value);
        }
 else         if (value instanceof Integer) {
          inner.putInt(args[1],(Integer)value);
        }
      }
    }
    return bundle;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}

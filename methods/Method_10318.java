@Override public boolean clearExpired(Date date){
  boolean clearedAny=false;
  SharedPreferences.Editor prefsWriter=cookiePrefs.edit();
  for (  ConcurrentHashMap.Entry<String,Cookie> entry : cookies.entrySet()) {
    String name=entry.getKey();
    Cookie cookie=entry.getValue();
    if (cookie.isExpired(date)) {
      cookies.remove(name);
      prefsWriter.remove(COOKIE_NAME_PREFIX + name);
      clearedAny=true;
    }
  }
  if (clearedAny) {
    prefsWriter.putString(COOKIE_NAME_STORE,TextUtils.join(",",cookies.keySet()));
  }
  prefsWriter.apply();
  return clearedAny;
}

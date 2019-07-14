@Override public boolean onCreate(){
  authority=getAuthority(getContext());
  if (ACRA.DEV_LOGGING)   ACRA.log.d(ACRA.LOG_TAG,"Registered content provider for authority " + authority);
  return true;
}

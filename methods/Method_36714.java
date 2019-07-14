public boolean isValid(){
  return (!TextUtils.isEmpty(stringType) || type >= 0) && serviceManager != null;
}

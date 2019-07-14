public int getErrorStringRes(){
  if (response == null) {
    return getErrorStringRes(this.getCause());
  }
  Integer StringRes=ERROR_CODE_STRING_RES_MAP.get(code);
  return StringRes != 0 ? StringRes : R.string.api_error_unknown;
}

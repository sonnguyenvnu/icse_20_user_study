protected final String _truncate(String desc){
  if (desc == null) {
    return "";
  }
  if (desc.length() <= MAX_ERROR_STR_LEN) {
    return desc;
  }
  return desc.substring(0,MAX_ERROR_STR_LEN) + "]...[" + desc.substring(desc.length() - MAX_ERROR_STR_LEN);
}

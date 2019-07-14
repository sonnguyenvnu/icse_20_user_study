public Long getInt(String key){
  Long ret=(Long)contents.get(key);
  if (ret == null) {
    return 0L;
  }
 else {
    return ret;
  }
}

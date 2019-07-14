private void update(String key,String value,String type){
  try {
    if (KEY_TYPE_INT.equals(type)) {
      mKeyValues.put(key,Integer.parseInt(value));
    }
 else     if (KEY_TYPE_BOOL.equals(type)) {
      mKeyValues.put(key,Boolean.parseBoolean(value));
    }
 else     if (KEY_TYPE_STRING.equals(type)) {
      mKeyValues.put(key,value);
    }
  }
 catch (  NumberFormatException e) {
    Timber.e("MmsConfig.update: invalid " + key + "," + value + "," + type);
  }
}

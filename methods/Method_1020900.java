public <T>T attr(String fieldName,Class<T> clzz){
  try {
    Field field=(this.getClass().getDeclaredField(fieldName));
    field.setAccessible(true);
    return clzz.cast(field.get(this));
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}

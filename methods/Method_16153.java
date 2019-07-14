public Object readByBytes(Class<?> clazz,byte[] bytes){
  if (clazz == String.class) {
    return new String(bytes,charset);
  }
  if (null != converters) {
    CustomMessageConverter converter=converters.stream().filter(cvt -> cvt.support(clazz)).findFirst().orElse(null);
    if (converter != null) {
      return converter.convert(clazz,bytes);
    }
  }
  Object object=JSON.parseObject(bytes,0,bytes.length,charset.newDecoder(),clazz);
  return object;
}

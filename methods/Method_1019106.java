/** 
 * When a new class instance is put into the map, the raw backing map should be updated too.
 * @param key Class name.
 * @param value ClassNode instance.
 * @return Previous associated value, if any.
 */
@Override public ClassNode put(String key,ClassNode value){
  try {
    byte[] classFile=ClassUtil.getBytes(value);
    raw.put(key,classFile);
  }
 catch (  Exception e) {
    Logging.warn("Failed to convert to raw byte[]: '" + value.name + "' due to the " + "following error: ");
    Logging.error(e);
  }
  return super.put(key,value);
}

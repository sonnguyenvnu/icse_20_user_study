/** 
 * ????
 * @param name ???
 * @return ??
 * @throws ReflectException
 */
private ReflectionUtils field(String name) throws ReflectException {
  try {
    Field field=field0(name);
    return on(field.get(object));
  }
 catch (  Exception e) {
    throw new ReflectException(e);
  }
}

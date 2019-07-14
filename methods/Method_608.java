/** 
 * @deprecated  internal method, dont call
 */
public static boolean isPrimitive2(Class<?> clazz){
  return clazz.isPrimitive() || clazz == Boolean.class || clazz == Character.class || clazz == Byte.class || clazz == Short.class || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class || clazz == BigInteger.class || clazz == BigDecimal.class || clazz == String.class || clazz == java.util.Date.class || clazz == java.sql.Date.class || clazz == java.sql.Time.class || clazz == java.sql.Timestamp.class || clazz.isEnum();
}

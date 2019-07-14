/** 
 * ??????????ID
 * @param methodSignature ????key
 * @return md5hex val
 */
public static String unitId(String methodSignature){
  return DigestUtils.md5DigestAsHex((APPLICATION_ID_WHEN_RUNNING + methodSignature).getBytes());
}

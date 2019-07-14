/** 
 * ????
 * @param exception
 * @param hostPort
 * @param currentTime(??)
 */
public static void collectException(Exception exception,String hostPort,long currentTime){
  collectException(exception,hostPort,currentTime,ClientExceptionType.REDIS_TYPE);
}

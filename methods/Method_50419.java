/** 
 * create custom thread factory.
 * @param namePrefix prefix
 * @param daemon     daemon
 * @return {@linkplain ThreadFactory}
 */
public static ThreadFactory create(final String namePrefix,final boolean daemon){
  return new HmilyThreadFactory(namePrefix,daemon);
}

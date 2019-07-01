/** 
 * Get a  {@link ThreadFactory} suitable for use in the current environment.
 * @param nameFormat to apply to threads created by the factory.
 * @param daemon     {@code true} if the threads the factory creates are daemon threads,{@code false} otherwise.
 * @return a {@link ThreadFactory}.
 */
public static ThreadFactory _XXXXX_(String nameFormat,boolean daemon){
  ThreadFactory threadFactory=MoreExecutors.platformThreadFactory();
  return new ThreadFactoryBuilder().setThreadFactory(threadFactory).setDaemon(daemon).setNameFormat(nameFormat).build();
}
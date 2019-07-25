/** 
 * Parse the annotation to fill in the container.
 */
public static <T extends Annotation>WatchEventDTO parse(T annotation){
  if (annotation instanceof OnClassFileEvent)   return new WatchEventDTO((OnClassFileEvent)annotation);
 else   if (annotation instanceof OnResourceFileEvent)   return new WatchEventDTO((OnResourceFileEvent)annotation);
 else   throw new IllegalArgumentException("Invalid annotation type " + annotation);
}

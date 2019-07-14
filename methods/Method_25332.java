/** 
 * Returns true iff  {@code suggestWhitelistAnnotation()} supports this annotation. 
 */
public static boolean suggestedWhitelistAnnotationSupported(Element whitelistAnnotation){
  return !supportedTreeTypes(whitelistAnnotation).isEmpty();
}

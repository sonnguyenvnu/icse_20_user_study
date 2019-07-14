/** 
 * Checks if  {@link ImagePipelineFactory} has already been initialized 
 */
public static synchronized boolean hasBeenInitialized(){
  return sInstance != null;
}

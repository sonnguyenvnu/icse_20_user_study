/** 
 * Extracts any registered native libraries from the classpath, creates links (or copies) as needed and loads those libraries marked for load.  This method is idempotent and will only do extraction and loading on the first call.  All subsequent calls will just return the list of already loaded native resources.
 * @return The native resources that were loaded.
 */
public ImmutableList<NativeResource> load(){
  return nativeResources.get();
}

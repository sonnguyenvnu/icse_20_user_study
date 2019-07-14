/** 
 * Get an identifier that can be used to resurrect this mode and connect it to a sketch. Using this instead of getTitle() because there might be name clashes with the titles, but there should not be once the actual package, et al. is included.
 * @return full name (package + class name) for this mode.
 */
public String getIdentifier(){
  return getClass().getCanonicalName();
}

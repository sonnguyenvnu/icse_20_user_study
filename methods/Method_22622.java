/** 
 * @return if not overridden, a folder named "sketchbook" in user.home.
 * @throws Exception so that subclasses can throw a fit
 */
public File getDefaultSketchbookFolder() throws Exception {
  return new File(System.getProperty("user.home"),"sketchbook");
}

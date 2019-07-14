/** 
 * See if the user has their own template for this Mode. If the default extension is "pde", this will look for a file called sketch.pde to use as the template for all sketches.
 */
protected File checkSketchbookTemplate(){
  File user=new File(Base.getSketchbookTemplatesFolder(),getTitle());
  if (user.exists()) {
    File template=new File(user,"sketch." + getDefaultExtension());
    if (template.exists() && template.canRead()) {
      return user;
    }
  }
  return null;
}

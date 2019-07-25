/** 
 * Writes a single view as a PlantUML diagram definition, to the specified writer.
 * @param view      the view to write
 * @param writer    the Writer to write the PlantUML definition to
 */
public void write(View view,Writer writer){
  if (view == null) {
    throw new IllegalArgumentException("A view must be provided.");
  }
  if (writer == null) {
    throw new IllegalArgumentException("A writer must be provided.");
  }
  if (SystemLandscapeView.class.isAssignableFrom(view.getClass())) {
    write((SystemLandscapeView)view,writer);
  }
 else   if (SystemContextView.class.isAssignableFrom(view.getClass())) {
    write((SystemContextView)view,writer);
  }
 else   if (ContainerView.class.isAssignableFrom(view.getClass())) {
    write((ContainerView)view,writer);
  }
 else   if (ComponentView.class.isAssignableFrom(view.getClass())) {
    write((ComponentView)view,writer);
  }
 else   if (DynamicView.class.isAssignableFrom(view.getClass())) {
    write((DynamicView)view,writer);
  }
 else   if (DeploymentView.class.isAssignableFrom(view.getClass())) {
    write((DeploymentView)view,writer);
  }
}

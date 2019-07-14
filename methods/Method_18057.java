/** 
 * @return A mounted view or null if this component does not mount a view.
 */
@Nullable public View getMountedView(){
  final Component component=mNode.getTailComponent();
  if (component != null && Component.isMountViewSpec(component)) {
    return (View)getMountedContent();
  }
  return null;
}

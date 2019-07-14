/** 
 * @return The text content of the component wrapped by the debug component, or null if noTextContent/TextView are found.
 */
@Nullable public String getTextContent(){
  final LithoView lithoView=getLithoView();
  if (lithoView == null) {
    return null;
  }
  final Component component=getComponent();
  final MountState mountState=lithoView.getMountState();
  for (int i=0, size=mountState.getItemCount(); i < size; i++) {
    final MountItem mountItem=mountState.getItemAt(i);
    final Component mountItemComponent=mountItem == null ? null : mountItem.getComponent();
    if (mountItemComponent != null && mountItemComponent.getId() == component.getId()) {
      final Object content=mountItem.getContent();
      final StringBuilder sb=new StringBuilder();
      if (content instanceof TextContent) {
        for (        CharSequence charSequence : ((TextContent)content).getTextItems()) {
          sb.append(charSequence);
        }
      }
 else       if (content instanceof TextView) {
        sb.append(((TextView)content).getText());
      }
      if (sb.length() != 0) {
        return sb.toString();
      }
    }
  }
  return null;
}

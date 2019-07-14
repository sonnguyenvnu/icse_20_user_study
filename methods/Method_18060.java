/** 
 * @return A concatenated string of all text content within the underlying LithoView. Null if thenode doesn't have an associated LithoView.
 */
@Nullable public String getAllTextContent(){
  final LithoView lithoView=getLithoView();
  if (lithoView == null) {
    return null;
  }
  final MountState mountState=lithoView.getMountState();
  final StringBuilder sb=new StringBuilder();
  for (int i=0, size=mountState.getItemCount(); i < size; i++) {
    final MountItem mountItem=mountState.getItemAt(i);
    final Component mountItemComponent=mountItem == null ? null : mountItem.getComponent();
    if (mountItemComponent != null) {
      final Object content=mountItem.getContent();
      if (content instanceof TextContent) {
        for (        CharSequence charSequence : ((TextContent)content).getTextItems()) {
          sb.append(charSequence);
        }
      }
 else       if (content instanceof TextView) {
        sb.append(((TextView)content).getText());
      }
    }
  }
  return sb.toString();
}

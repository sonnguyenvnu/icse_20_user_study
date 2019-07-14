/** 
 * Appends a compact description of a  {@link DebugComponent} for debugging purposes.
 * @param left The left coordinate of the {@link DebugComponent}
 * @param top The top coordinate of the {@link DebugComponent}
 * @param debugComponent The {@link DebugComponent}
 * @param sb The {@link StringBuilder} to which the description is appended
 * @param embedded Whether the call is embedded in "adb dumpsys activity"
 */
@DoNotStrip public static void addViewDescription(int left,int top,DebugComponent debugComponent,StringBuilder sb,boolean embedded){
  sb.append("litho.");
  sb.append(debugComponent.getComponent().getSimpleName());
  sb.append('{');
  sb.append(Integer.toHexString(debugComponent.hashCode()));
  sb.append(' ');
  final LithoView lithoView=debugComponent.getLithoView();
  final DebugLayoutNode layout=debugComponent.getLayoutNode();
  sb.append(lithoView != null && lithoView.getVisibility() == View.VISIBLE ? "V" : ".");
  sb.append(layout != null && layout.getFocusable() ? "F" : ".");
  sb.append(lithoView != null && lithoView.isEnabled() ? "E" : ".");
  sb.append(".");
  sb.append(lithoView != null && lithoView.isHorizontalScrollBarEnabled() ? "H" : ".");
  sb.append(lithoView != null && lithoView.isVerticalScrollBarEnabled() ? "V" : ".");
  sb.append(layout != null && layout.getClickHandler() != null ? "C" : ".");
  sb.append(". .. ");
  final Rect bounds=debugComponent.getBounds();
  sb.append(left + bounds.left);
  sb.append(",");
  sb.append(top + bounds.top);
  sb.append("-");
  sb.append(left + bounds.right);
  sb.append(",");
  sb.append(top + bounds.bottom);
  final String testKey=debugComponent.getTestKey();
  if (testKey != null && !TextUtils.isEmpty(testKey)) {
    sb.append(String.format(" litho:id/%s",testKey.replace(' ','_')));
  }
  final String textContent=debugComponent.getTextContent();
  if (textContent != null && !TextUtils.isEmpty(textContent)) {
    sb.append(String.format(" text=\"%s\"",textContent.replace("\n","").replace("\"","")));
  }
  if (!embedded && layout != null && layout.getClickHandler() != null) {
    sb.append(" [clickable]");
  }
  sb.append('}');
}

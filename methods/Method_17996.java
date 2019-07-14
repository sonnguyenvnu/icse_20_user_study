/** 
 * Logs the content of a single debug component instance 
 */
private static void logComponent(@Nullable DebugComponent debugComponent,int depth,StringBuilder sb){
  if (debugComponent == null) {
    return;
  }
  sb.append(debugComponent.getComponent().getSimpleName());
  sb.append('{');
  final LithoView lithoView=debugComponent.getLithoView();
  final DebugLayoutNode layout=debugComponent.getLayoutNode();
  sb.append(lithoView != null && lithoView.getVisibility() == View.VISIBLE ? "V" : "H");
  if (layout != null && layout.getClickHandler() != null) {
    sb.append(" [clickable]");
  }
  sb.append('}');
  for (  DebugComponent child : debugComponent.getChildComponents()) {
    sb.append("\n");
    for (int i=0; i <= depth; i++) {
      sb.append("  ");
    }
    logComponent(child,depth + 1,sb);
  }
}

/** 
 * The root represents a DebugSection with information about the root of the SectionTree. 
 */
public static @Nullable DebugSection getRootInstance(List<View> lithoViews){
  if (lithoViews == null || lithoViews.isEmpty()) {
    return null;
  }
  final Section renderInfoSection=(Section)RenderInfoDebugInfoRegistry.getRenderInfoSectionDebugInfo(lithoViews.get(0));
  return getRootDebugSection(renderInfoSection,lithoViews);
}

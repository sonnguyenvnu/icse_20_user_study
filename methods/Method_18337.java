/** 
 * Turn the extracted tree props from a  {@link ComponentsLogger} and turn them into a singlecolon-separated string that
 * @see #populatePerfEventFromLogger(ComponentContext,ComponentsLogger,PerfEvent)
 * @param component Component to extract tree props from.
 * @param logger
 * @return String of extracted props with key-value pairs separated by ':'.
 */
@Nullable public static String getAnnotationBundleFromLogger(Component component,ComponentsLogger logger){
  @Nullable final ComponentContext scopedContext=component.getScopedContext();
  if (scopedContext == null) {
    return null;
  }
  @Nullable final TreeProps treeProps=scopedContext.getTreeProps();
  if (treeProps == null) {
    return null;
  }
  @Nullable final Map<String,String> extraAnnotations=logger.getExtraAnnotations(treeProps);
  if (extraAnnotations == null) {
    return null;
  }
  final StringBuilder sb=new StringBuilder(extraAnnotations.size() * 16);
  for (  Map.Entry<String,String> entry : extraAnnotations.entrySet()) {
    sb.append(entry.getKey());
    sb.append(':');
    sb.append(entry.getValue());
    sb.append(':');
  }
  return sb.toString();
}

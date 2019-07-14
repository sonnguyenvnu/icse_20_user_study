/** 
 * Annotate a log event with the log tag set in the context, and extract the treeprops from a given  {@link ComponentContext} and convert them into perf event annotations using a {@link ComponentsLogger} implementation.
 * @return Annotated perf event, or <code>null</code> if the resulting event isn't deemed worthyof reporting.
 */
@Nullable @CheckReturnValue public static PerfEvent populatePerfEventFromLogger(ComponentContext c,ComponentsLogger logger,@Nullable PerfEvent perfEvent){
  if (perfEvent == null) {
    return null;
  }
  final String logTag=c.getLogTag();
  if (logTag == null) {
    logger.cancelPerfEvent(perfEvent);
    return null;
  }
  perfEvent.markerAnnotate(FrameworkLogEvents.PARAM_LOG_TAG,logTag);
  @Nullable final TreeProps treeProps=c.getTreeProps();
  if (treeProps == null) {
    return perfEvent;
  }
  @Nullable final Map<String,String> extraAnnotations=logger.getExtraAnnotations(treeProps);
  if (extraAnnotations == null) {
    return perfEvent;
  }
  for (  Map.Entry<String,String> e : extraAnnotations.entrySet()) {
    perfEvent.markerAnnotate(e.getKey(),e.getValue());
  }
  return perfEvent;
}

/** 
 * Create a performance event that will add the names of the current and next section as params.
 */
@Nullable public static PerfEvent getSectionsPerformanceEvent(ComponentContext c,int eventId,Section currentSection,Section nextSection){
  final ComponentsLogger logger=c.getLogger();
  if (logger == null) {
    return null;
  }
  final PerfEvent logEvent=LogTreePopulator.populatePerfEventFromLogger(c,logger,logger.newPerformanceEvent(c,eventId));
  if (logEvent != null) {
    logEvent.markerAnnotate(PARAM_SECTION_CURRENT,currentSection == null ? "null" : currentSection.getSimpleName());
    logEvent.markerAnnotate(PARAM_SECTION_NEXT,nextSection == null ? "null" : nextSection.getSimpleName());
  }
  return logEvent;
}

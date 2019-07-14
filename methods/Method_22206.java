/** 
 * {@inheritDoc}
 */
@Override public void collectApplicationStartUp(@NonNull Context context,@NonNull CoreConfiguration config){
  if (config.reportContent().contains(ReportField.INITIAL_CONFIGURATION)) {
    initialConfiguration=collectConfiguration(context);
  }
}

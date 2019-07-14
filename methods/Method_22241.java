/** 
 * Use this if you want to keep the default configuration of reportContent, but set some fields explicitly.
 * @param field  the field to set
 * @param enable if this field should be reported
 */
@BuilderMethod public void setReportField(@NonNull ReportField field,boolean enable){
  this.reportContentChanges.put(field,enable);
}

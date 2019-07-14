/** 
 * Stores the mappings in this Properties to the specified OutputStream, putting the specified comment at the beginning. The output from this method is suitable for being read by the load() method.
 * @param crashData CrashReportData to save.
 * @param file      File into which to store the CrashReportData.
 * @throws IOException   if the CrashReportData could not be written to the OutputStream.
 * @throws JSONException if the crashData could not be converted to JSON.
 */
public void store(@NonNull CrashReportData crashData,@NonNull File file) throws IOException, JSONException {
  IOUtils.writeStringToFile(file,crashData.toJSON());
}

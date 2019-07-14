/** 
 * Loads properties from the specified  {@code File}.
 * @param file Report file from which to load the CrashData.
 * @return CrashReportData read from the supplied File.
 * @throws IOException   if error occurs during reading from the {@code File}.
 * @throws JSONException if the stream cannot be parsed as a JSON object.
 */
@NonNull public CrashReportData load(@NonNull File file) throws IOException, JSONException {
  return new CrashReportData(new StreamReader(file).read());
}

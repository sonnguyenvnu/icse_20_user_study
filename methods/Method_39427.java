/** 
 * Convert Java Properties to Jodd Props format
 * @param writer     Writer to write Props formatted file content to
 * @param properties Properties to convert to Props format
 * @param profiles   Properties per profile to convert and add to the Props format
 * @throws IOException On any I/O error when writing to the writer
 */
void convertToWriter(final Writer writer,final Properties properties,final Map<String,Properties> profiles) throws IOException {
  final BufferedWriter bw=getBufferedWriter(writer);
  writeBaseAndProfileProperties(bw,properties,profiles);
  writeProfilePropertiesThatAreNotInTheBase(bw,properties,profiles);
  bw.flush();
}

/** 
 * Performs the typical spotlessApply, but with PaddedCell handling of misbehaving FormatterSteps. 
 */
public static void apply(Formatter formatter,File file) throws IOException {
  applyAnyChanged(formatter,file);
}

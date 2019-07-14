/** 
 * Returns a stream of trace events. 
 */
private LongStream eventStream() throws IOException {
  if (settings.isSynthetic()) {
    return Synthetic.generate(settings);
  }
  List<String> filePaths=settings.traceFiles().paths();
  TraceFormat format=settings.traceFiles().format();
  return format.readFiles(filePaths).events();
}

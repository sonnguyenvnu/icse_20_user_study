/** 
 * Returns the list, possibly empty but never  {@code null}, of errors and warnings generated in the file.
 */
public List<Diagnostic> getDiagnosticsForFile(String file){
  List<Diagnostic> errs=problems.get(file);
  if (errs != null) {
    return errs;
  }
  return new ArrayList<Diagnostic>();
}

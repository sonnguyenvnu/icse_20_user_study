public List<Diagnostic> getDiagnosticsForFile(String file){
  List<Diagnostic> errs=semanticErrors.get(file);
  if (errs != null) {
    return errs;
  }
  return new ArrayList<>();
}

private static long countErrors(DiagnosticCollector<JavaFileObject> diagnosticCollector){
  return diagnosticCollector.getDiagnostics().stream().filter(d -> d.getKind() == Diagnostic.Kind.ERROR).count();
}

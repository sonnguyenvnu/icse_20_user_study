@Override public void onDescribed(Description description){
  List<AppliedFix> appliedFixes=description.fixes.stream().filter(f -> !shouldSkipImportTreeFix(description.position,f)).map(fixToAppliedFix).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
  String message=messageForFixes(description,appliedFixes);
  JavaFileObject originalSource=log.useSource(sourceFile);
  try {
    JCDiagnostic.Factory factory=JCDiagnostic.Factory.instance(context);
    JCDiagnostic.DiagnosticType type=JCDiagnostic.DiagnosticType.ERROR;
    DiagnosticPosition pos=description.position;
switch (description.severity) {
case ERROR:
      if (dontUseErrors) {
        type=JCDiagnostic.DiagnosticType.WARNING;
      }
 else {
        type=JCDiagnostic.DiagnosticType.ERROR;
      }
    break;
case WARNING:
  type=JCDiagnostic.DiagnosticType.WARNING;
break;
case SUGGESTION:
type=JCDiagnostic.DiagnosticType.NOTE;
break;
}
log.report(factory.create(type,log.currentSource(),pos,MESSAGE_BUNDLE_KEY,message));
}
  finally {
if (originalSource != null) {
log.useSource(originalSource);
}
}
}

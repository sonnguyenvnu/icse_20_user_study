private static boolean shouldSkipImportTreeFix(DiagnosticPosition position,Fix f){
  if (position.getTree() != null && position.getTree().getKind() != Kind.IMPORT) {
    return false;
  }
  return !f.getImportsToAdd().isEmpty() || !f.getImportsToRemove().isEmpty();
}

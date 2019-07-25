public boolean overrides(NonTypesystemRule_Runtime rule){
  if (rule instanceof check_FieldIsNeverUsedOrAssigned_NonTypesystemRule) {
    return true;
  }
  if (rule instanceof check_FieldDeclarationCanBeLocalVariable_NonTypesystemRule) {
    return true;
  }
  return false;
}

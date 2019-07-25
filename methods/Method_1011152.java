public boolean overrides(NonTypesystemRule_Runtime rule){
  if (rule instanceof check_ClassifierType_NonTypesystemRule) {
    return true;
  }
  return false;
}

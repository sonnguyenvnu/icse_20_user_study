public void select(@NotNull SLanguage language){
  SModuleReference smr=language.getSourceModuleReference();
  if (smr != null) {
    select(smr);
  }
}

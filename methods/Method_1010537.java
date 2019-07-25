/** 
 * For each collected module: 1) update all module dependencies according to  {@link #myModuleReferenceMap}For each collected model: 1) update all model references in model imports according to  {@link #myModelReferenceMap}2) update all languages imports according to  {@link #myUsedLanguagesMap}3) update all model references in it's nodes according to  {@link #myModelReferenceMap}It saves all models after references updating. Note that after calling this method you can't use this instance to update references.
 */
public void adjust() throws RefUpdateException {
  assertNotAdjusted();
  myModules.forEach(module -> {
    if (module instanceof Generator) {
      adjustGenerator((Generator)module);
    }
 else     if (module instanceof Language) {
      adjustLanguage((Language)module);
    }
 else     if (module instanceof AbstractModule) {
      adjustModule((AbstractModule)module);
    }
  }
);
  myModels.forEach(model -> {
    SModelInternal modelInternal=(SModelInternal)model;
    for (    SModelReference aImport : modelInternal.getModelImports()) {
      if (myModelReferenceMap.containsKey(aImport)) {
        modelInternal.deleteModelImport(aImport);
        modelInternal.addModelImport(myModelReferenceMap.get(aImport));
      }
    }
    List<SLanguage> usedLanguages=new ArrayList<>(modelInternal.importedLanguageIds());
    for (    SLanguage usedLanguage : usedLanguages) {
      if (myUsedLanguagesMap.containsKey(usedLanguage)) {
        modelInternal.deleteLanguageId(usedLanguage);
        modelInternal.addLanguage(myUsedLanguagesMap.get(usedLanguage));
      }
    }
    model.getRootNodes().forEach(this::updateReferences);
  }
);
  myModels.forEach((model -> {
    if (model instanceof EditableSModel) {
      ((EditableSModel)model).setChanged(true);
      ((EditableSModel)model).save();
    }
  }
));
  myAdjusted=true;
}

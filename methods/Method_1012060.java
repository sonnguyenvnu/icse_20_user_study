@Override public void init(){
  if (!(myItem instanceof DevkitDescriptor)) {
    myRepository.getModelAccess().runReadAction(() -> {
      for (      Dependency dependency : myItem.getDependencies()) {
        SModuleReference moduleReference=dependency.getModuleRef();
        final SModule module=moduleReference.resolve(myRepository);
        if (module instanceof Language) {
          addLanguageItem(dependency);
        }
 else         if (module instanceof Generator) {
          addGeneratorItem(dependency);
        }
 else {
          addUnspecifiedItem(dependency);
        }
      }
    }
);
  }
  if (myItem instanceof LanguageDescriptor) {
    LanguageDescriptor languageDescriptor=(LanguageDescriptor)myItem;
    for (    SModuleReference moduleReference : languageDescriptor.getExtendedLanguages()) {
      addLanguageItem(new Dependency(moduleReference,SDependencyScope.EXTENDS));
    }
  }
 else   if (myItem instanceof GeneratorDescriptor) {
    GeneratorDescriptor generatorDescriptor=(GeneratorDescriptor)myItem;
    for (    SModuleReference moduleReference : generatorDescriptor.getDepGenerators()) {
      addGeneratorItem(new Dependency(moduleReference,SDependencyScope.EXTENDS));
    }
  }
 else   if (myItem instanceof DevkitDescriptor) {
    DevkitDescriptor devkitDescriptor=(DevkitDescriptor)myItem;
    for (    SModuleReference moduleReference : devkitDescriptor.getExtendedDevkits()) {
      addDevkitItem(new Dependency(moduleReference,SDependencyScope.EXTENDS));
    }
    for (    SModuleReference lang : devkitDescriptor.getExportedLanguages()) {
      addLanguageItem(new Dependency(lang,SDependencyScope.EXTENDS));
    }
    for (    SModuleReference moduleReference : devkitDescriptor.getExportedSolutions()) {
      addSolutionItem(new Dependency(moduleReference,SDependencyScope.EXTENDS));
    }
  }
}

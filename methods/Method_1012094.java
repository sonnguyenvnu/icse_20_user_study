private List<DepLink> dependencies(DependencyUtil.Role role,SModuleReference moduleRef){
  List<DepLink> result=ListSequence.fromList(new ArrayList<DepLink>());
  SModule module=moduleRef.resolve(myRepo);
  if (module == null) {
    return result;
  }
switch (role) {
case OwnedGenerator:
case None:
    addUsedLanguagesAndDevkitsOf(module,result,true);
  for (  SDependency dep : module.getDeclaredDependencies()) {
    if (dep.getScope() == SDependencyScope.DESIGN && !(myNeedRuntime)) {
      continue;
    }
    ListSequence.fromList(result).addElement(regularDependencyPresentation(dep));
  }
if (module instanceof Language) {
  for (  Generator g : ((Language)module).getGenerators()) {
    ListSequence.fromList(result).addElement(new DepLink(g.getModuleReference(),DependencyUtil.Role.OwnedGenerator,DependencyUtil.LinkType.Generator));
  }
}
if (module instanceof Generator) {
SLanguage srcLang=((Generator)module).sourceLanguage();
ListSequence.fromList(result).addElement(new DepLink(srcLang.getSourceModuleReference(),DependencyUtil.Role.SourceLanguage,DependencyUtil.LinkType.GeneratorLanguage));
}
break;
case UsedDevkit:
case DependencyDevkit:
if (!(module instanceof AbstractModule)) {
break;
}
DevkitDescriptor devkit=as_he47wm_a0a1a3d0g(((AbstractModule)module).getModuleDescriptor(),DevkitDescriptor.class);
if (devkit == null) {
break;
}
boolean direct=role == DependencyUtil.Role.UsedDevkit;
addDeps(result,devkit.getExtendedDevkits(),(direct ? DependencyUtil.Role.UsedDevkit : DependencyUtil.Role.DependencyDevkit),DependencyUtil.LinkType.ExtendsDevkit);
addDeps(result,devkit.getExportedLanguages(),(direct ? DependencyUtil.Role.UsedLanguage : DependencyUtil.Role.DependencyLanguage),DependencyUtil.LinkType.ExportsLanguage);
addDeps(result,devkit.getExportedSolutions(),(direct ? DependencyUtil.Role.RegularDependency : DependencyUtil.Role.RuntimeDependency),DependencyUtil.LinkType.ExportsSolution);
break;
case UsedLanguage:
addExtendedLanguages(module,DependencyUtil.Role.UsedLanguage,result);
if (myNeedRuntime) {
for (SDependency dep : module.getDeclaredDependencies()) {
if (dep.getScope() == SDependencyScope.DESIGN || dep.getScope() == SDependencyScope.EXTENDS) {
continue;
}
ListSequence.fromList(result).addElement(regularDependencyPresentation(dep));
}
addDeps(result,as_he47wm_a0b0b0b0e3a6(module,Language.class).getRuntimeModulesReferences(),DependencyUtil.Role.RuntimeDependency,DependencyUtil.LinkType.ExportsRuntime);
}
break;
case RegularDependency:
for (SDependency dep : module.getDeclaredDependencies()) {
if (dep.getScope() == SDependencyScope.DESIGN) {
continue;
}
if (dep.isReexport()) {
ListSequence.fromList(result).addElement(regularDependencyPresentation(dep));
}
 else if (myNeedRuntime) {
ListSequence.fromList(result).addElement(new DepLink(dep.getTargetModule(),DependencyUtil.Role.RuntimeDependency,DependencyUtil.LinkType.Depends));
}
}
if (myNeedRuntime) {
addUsedLanguagesAndDevkitsOf(module,result,false);
}
break;
case RuntimeDependency:
if (myNeedRuntime) {
for (SDependency dep : module.getDeclaredDependencies()) {
if (dep.getScope() == SDependencyScope.DESIGN) {
continue;
}
ListSequence.fromList(result).addElement(new DepLink(dep.getTargetModule(),DependencyUtil.Role.RuntimeDependency,(dep.isReexport() ? DependencyUtil.LinkType.ReexportsDep : DependencyUtil.LinkType.Depends)));
}
addUsedLanguagesAndDevkitsOf(module,result,false);
}
if (module instanceof Generator) {
ListSequence.fromList(result).addElement(new DepLink((as_he47wm_a0a0a0a0a0a1a6d0g(module,Generator.class)).sourceLanguage().getSourceModuleReference(),DependencyUtil.Role.RuntimeDependency,DependencyUtil.LinkType.GeneratorLanguage));
}
break;
case SourceLanguage:
addExtendedLanguages(module,DependencyUtil.Role.SourceLanguage,result);
if (myNeedRuntime) {
addDeps(result,as_he47wm_a0b0a0c0h3a6(module,Language.class).getRuntimeModulesReferences(),DependencyUtil.Role.RuntimeDependency,DependencyUtil.LinkType.ExportsRuntime);
}
break;
case DependencyLanguage:
addExtendedLanguages(module,DependencyUtil.Role.DependencyLanguage,result);
addDeps(result,as_he47wm_a0b0b0i3a6(module,Language.class).getRuntimeModulesReferences(),DependencyUtil.Role.RuntimeDependency,DependencyUtil.LinkType.ExportsRuntime);
break;
default :
}
return result;
}

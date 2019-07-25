/** 
 * All these checks whether the module is not disposed are due to the problem of idea plugin project being a MPSProject which is absolutely incorrect (since the project file does not contain a descriptor, there are no virtual folders and there is no need in ProjectDescriptor filling the project with modules, since idea modules contribute mps modules via MPSFacet. The MPSFacet is also responsible for disposing the corresponding SModule there thus we might get disposed modules in the plugin environment
 */
void update(ProgressMonitor monitor,@NotNull FileSystemEvent event){
  final SRepository repo=myMpsProject.getRepository();
  repo.getModelAccess().runWriteAction(() -> {
    final Map<SModuleReference,IFile> mRefs2Remove=myProjectModulesAndFiles.getAffectedBy(event.getRemoved());
    mRefs2Remove.keySet().forEach(mRef -> {
      ModulePath path=myMpsProject.getPath(mRef);
      if (path != null) {
        moduleNotFound(path);
      }
      SModule resolved=mRef.resolve(repo);
      if (resolved != null) {
        myMpsProject.removeModule0(resolved);
      }
    }
);
    mRefs2Remove.values().forEach(f -> f.removeListener(myRedispatchListener));
    final Map<SModuleReference,IFile> toReload=myProjectModulesAndFiles.getTrackedFor(event.getChanged());
    toReload.keySet().forEach(mRef -> {
      SModule module=mRef.resolve(repo);
      if (module instanceof AbstractModule) {
        SModuleOperations.reloadFromDisk((AbstractModule)module);
      }
    }
);
  }
);
}

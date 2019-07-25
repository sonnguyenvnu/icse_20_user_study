/** 
 * TODO move or rename the ModuleMaker (the naming is quite disturbing)
 */
public void clean(final Set<? extends SModule> modules,@NotNull final ProgressMonitor monitor){
  monitor.start("Cleaning...",modules.size());
  try {
    for (    SModule module : modules) {
      if (monitor.isCanceled()) {
        break;
      }
      if (!ModulesContainer.isExcluded(module)) {
        monitor.step(module.getModuleName());
        JavaModuleFacet facet=module.getFacet(JavaModuleFacet.class);
        assert facet != null && facet.getClassesGen() != null;
        File classesGenFile=new File(facet.getClassesGen().getPath());
        FileUtil.delete(classesGenFile);
      }
      monitor.advance(1);
    }
  }
  finally {
    monitor.done();
  }
}

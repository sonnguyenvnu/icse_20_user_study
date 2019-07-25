/** 
 * Returns a new module that installs all of  {@code modules}.
 */
public static Module combine(Iterable<? extends Module> modules){
  final Set<? extends Module> modulesSet=newHashSet(modules);
  return new Module(){
    @Override public void configure(    Binder binder){
      binder=binder.skipSources(getClass());
      for (      Module module : modulesSet) {
        binder.install(module);
      }
    }
  }
;
}

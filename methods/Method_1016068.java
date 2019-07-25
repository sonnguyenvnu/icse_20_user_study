@Override public Collection<Module> call(Collection<Module> modules){
  Module current=new AbstractModule(){
    @Override protected void configure(){
    }
  }
;
  for (  Module module : modules) {
    current=Modules.override(current).with(module);
  }
  return ImmutableList.of(current);
}

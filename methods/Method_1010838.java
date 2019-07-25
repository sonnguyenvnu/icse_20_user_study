@Override public void update(AnActionEvent e){
  Module module=LangDataKeys.MODULE.getData(e.getDataContext());
  if (module == null) {
    e.getPresentation().setVisible(false);
    return;
  }
 else {
    ModuleMPSSupport mpsSupport=ModuleMPSSupport.getInstance();
    boolean thereAreModels=mpsSupport != null && mpsSupport.isMPSEnabled(module) && thereAreModels(mpsSupport,module);
    e.getPresentation().setEnabled(thereAreModels);
  }
}

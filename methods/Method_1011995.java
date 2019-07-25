@Override public void render(@NotNull final SModuleReference element,@NotNull final ElementDescriptor presentation){
  myRepo.getModelAccess().runReadAction(() -> {
    SModule module=element.resolve(myRepo);
    if (module != null) {
      presentation.name=module.getModuleName();
      presentation.icon=GlobalIconManager.getInstance().getIconFor(module);
    }
 else {
      presentation.name=element.getModuleName();
      presentation.icon=IdeIcons.UNKNOWN_ICON;
    }
  }
);
}

@Override public void render(@NotNull final SModelReference element,@NotNull final ElementDescriptor presentation){
  myRepo.getModelAccess().runReadAction(() -> {
    SModel model=element.resolve(myRepo);
    if (model != null) {
      presentation.name=model.getName().getValue();
      presentation.location=model.getModule().getModuleName();
      presentation.icon=GlobalIconManager.getInstance().getIconFor(model);
    }
 else {
      presentation.name=element.getName().getValue();
      presentation.location="unknown";
      presentation.icon=IdeIcons.UNKNOWN_ICON;
    }
  }
);
}

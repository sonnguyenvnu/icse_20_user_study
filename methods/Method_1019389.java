void hydrate(Model model){
  this.model=model;
  for (  SystemLandscapeView view : systemLandscapeViews) {
    view.setModel(model);
    hydrateView(view);
  }
  for (  SystemContextView view : systemContextViews) {
    view.setSoftwareSystem(model.getSoftwareSystemWithId(view.getSoftwareSystemId()));
    hydrateView(view);
  }
  for (  ContainerView view : containerViews) {
    view.setSoftwareSystem(model.getSoftwareSystemWithId(view.getSoftwareSystemId()));
    hydrateView(view);
  }
  for (  ComponentView view : componentViews) {
    view.setSoftwareSystem(model.getSoftwareSystemWithId(view.getSoftwareSystemId()));
    view.setContainer(view.getSoftwareSystem().getContainerWithId(view.getContainerId()));
    hydrateView(view);
  }
  for (  DynamicView view : dynamicViews) {
    if (!isNullOrEmpty(view.getElementId())) {
      view.setElement(model.getElement(view.getElementId()));
    }
    view.setModel(model);
    hydrateView(view);
  }
  for (  DeploymentView view : deploymentViews) {
    if (!isNullOrEmpty(view.getSoftwareSystemId())) {
      view.setSoftwareSystem(model.getSoftwareSystemWithId(view.getSoftwareSystemId()));
    }
    view.setModel(model);
    hydrateView(view);
  }
  for (  FilteredView filteredView : filteredViews) {
    filteredView.setView(getViewWithKey(filteredView.getBaseViewKey()));
  }
}

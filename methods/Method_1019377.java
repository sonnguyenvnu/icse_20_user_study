void hydrate(){
  people.forEach(this::addElementToInternalStructures);
  for (  SoftwareSystem softwareSystem : softwareSystems) {
    addElementToInternalStructures(softwareSystem);
    for (    Container container : softwareSystem.getContainers()) {
      addElementToInternalStructures(container);
      container.setParent(softwareSystem);
      for (      Component component : container.getComponents()) {
        addElementToInternalStructures(component);
        component.setParent(container);
      }
    }
  }
  deploymentNodes.forEach(dn -> hydrateDeploymentNode(dn,null));
  getElements().forEach(this::hydrateRelationships);
}

protected <T extends JComponent & UriGettable>T load(API api,File file,Path rootPath){
  ContainerEntry parentEntry=new ContainerEntry(file);
  ContainerFactory containerFactory=api.getContainerFactory(rootPath);
  if (containerFactory != null) {
    Container container=containerFactory.make(api,parentEntry,rootPath);
    if (container != null) {
      parentEntry.setChildren(container.getRoot().getChildren());
      PanelFactory panelFactory=api.getMainPanelFactory(container);
      if (panelFactory != null) {
        T mainPanel=panelFactory.make(api,container);
        if (mainPanel != null) {
          TreeNodeFactory treeNodeFactory=api.getTreeNodeFactory(parentEntry);
          Object data=(treeNodeFactory != null) ? treeNodeFactory.make(api,parentEntry).getUserObject() : null;
          Icon icon=(data instanceof TreeNodeData) ? ((TreeNodeData)data).getIcon() : null;
          String location=file.getPath();
          api.addPanel(file.getName(),icon,"Location: " + location,mainPanel);
          return mainPanel;
        }
      }
    }
  }
  return null;
}

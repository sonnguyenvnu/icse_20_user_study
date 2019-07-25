@Override public void apply() throws ConfigurationException {
  List<ContainerFile> containerFiles=new ArrayList<>();
  for (  ContainerFile containerFile : this.tableView.getListTableModel().getItems()) {
    containerFiles.add(new ContainerFile(containerFile.getPath()));
  }
  getSettings().containerFiles=containerFiles;
  this.changed=false;
}

@Override public void apply() throws ConfigurationException {
  List<TwigNamespaceSetting> twigPaths=new ArrayList<>();
  for (  TwigPath twigPath : this.tableView.getListTableModel().getItems()) {
    if ((!twigPath.isEnabled() && twigPath.getRelativePath(this.project) != null) || twigPath.isCustomPath()) {
      twigPaths.add(new TwigNamespaceSetting(twigPath.getNamespace(),twigPath.getRelativePath(this.project),twigPath.isEnabled(),twigPath.getNamespaceType(),twigPath.isCustomPath()));
    }
  }
  getSettings().twigNamespaces=twigPaths;
  this.changed=false;
}

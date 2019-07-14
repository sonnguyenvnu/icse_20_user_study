public void doUpgrade(Map<String,Object> context,SystemVersion.Dependency installed){
  SimpleDependencyUpgrader simpleDependencyUpgrader=new SimpleDependencyUpgrader(installed,dependency,context);
  context.put("upgrader",simpleDependencyUpgrader);
  if (upgrader != null) {
    upgrader.execute(context);
  }
}

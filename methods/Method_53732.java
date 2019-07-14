private static void topologicalSort(List<Module> modules){
  Map<String,Module> moduleRegistry=new HashMap<>(modules.size());
  for (  Module module : modules) {
    moduleRegistry.put(module.name,module);
  }
  List<Module> stack=new ArrayList<>(modules.size());
  Set<Module> visited=new HashSet<>(modules.size());
  Set<Module> expanded=new HashSet<>(modules.size());
  for (  Module node : modules) {
    traverse(node,moduleRegistry,stack,visited,expanded);
  }
  modules.clear();
  modules.addAll(stack);
}

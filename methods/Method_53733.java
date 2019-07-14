private static void push(List<Module> stack,Module module){
  int i=stack.size();
  while (0 < i) {
    Module sibling=stack.get(i - 1);
    if (module.dependencies.contains(sibling.name)) {
      break;
    }
    if (sibling.name.compareTo(module.name) < 0) {
      break;
    }
    --i;
  }
  stack.add(i,module);
}

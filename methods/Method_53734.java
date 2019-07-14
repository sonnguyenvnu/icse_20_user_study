private void compileModuleInfo(Module module,Set<String> javaOnly) throws IOException {
  String modulePath=Stream.concat(module.dependencies.stream(),Stream.of(module.name)).map(it -> "bin/classes/lwjgl/" + it).collect(Collectors.joining(File.pathSeparator));
  compileModuleInfo(module.name,module.info,modulePath);
  if (!javaOnly.contains(module.name)) {
    compileModuleInfo(module.name + ".natives","module " + module.nameJava + ".natives {\n" + "    requires transitive " + module.nameJava + ";\n" + "}",modulePath);
  }
}

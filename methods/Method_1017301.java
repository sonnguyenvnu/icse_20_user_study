@Nullable public static Object run(@NotNull Project project,@NotNull String className,@NotNull String serviceJsNameStrategy) throws ScriptException {
  JsonObject jsonObject=new JsonObject();
  jsonObject.addProperty("className",className);
  jsonObject.addProperty("projectName",project.getName());
  jsonObject.addProperty("projectBasePath",project.getBasePath());
  jsonObject.addProperty("defaultNaming",new DefaultServiceNameStrategy().getServiceName(new ServiceNameStrategyParameter(project,className)));
  PhpClass aClass=PhpElementsUtil.getClass(project,className);
  if (aClass != null) {
    String relativePath=VfsUtil.getRelativePath(aClass.getContainingFile().getVirtualFile(),project.getBaseDir(),'/');
    if (relativePath != null) {
      jsonObject.addProperty("relativePath",relativePath);
    }
    jsonObject.addProperty("absolutePath",aClass.getContainingFile().getVirtualFile().toString());
  }
  ScriptEngine engine=new ScriptEngineManager().getEngineByName("JavaScript");
  if (engine == null) {
    return null;
  }
  return engine.eval("var __p = eval(" + jsonObject.toString() + "); result = function(args) { " + serviceJsNameStrategy + " }(__p)");
}

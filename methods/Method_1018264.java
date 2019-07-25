/** 
 * Launch the ark container. This method is the initial entry point when execute in IDE.
 * @throws Exception if the ark container fails to launch.
 */
public Object launch(String[] args,String classpath,Method method) throws Exception {
  JarFile.registerUrlProtocolHandler();
  ClassLoader classLoader=createContainerClassLoader(getContainerArchive());
  List<String> attachArgs=new ArrayList<>();
  attachArgs.add(String.format("%s%s=%s",CommandArgument.ARK_CONTAINER_ARGUMENTS_MARK,CommandArgument.CLASSPATH_ARGUMENT_KEY,classpath));
  attachArgs.add(String.format("%s%s=%s",CommandArgument.ARK_BIZ_ARGUMENTS_MARK,CommandArgument.ENTRY_CLASS_NAME_ARGUMENT_KEY,method.getDeclaringClass().getName()));
  attachArgs.add(String.format("%s%s=%s",CommandArgument.ARK_BIZ_ARGUMENTS_MARK,CommandArgument.ENTRY_METHOD_NAME_ARGUMENT_KEY,method.getName()));
  attachArgs.addAll(Arrays.asList(args));
  return launch(attachArgs.toArray(new String[attachArgs.size()]),getMainClass(),classLoader);
}

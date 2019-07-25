public static String check(@Nullable final SRepository repo,String extension,final String namespace,String rootPath){
  if (MPSExtentions.DOT_LANGUAGE.equals(extension) && !(SourceVersion.isName(namespace))) {
    return "Language namespace should be valid Java package";
  }
  if (rootPath.length() == 0) {
    return "Path should be specified";
  }
  String message=NewModuleCheckUtil.checkModuleDirectory(new File(rootPath),extension,"Module");
  if (message != null) {
    return message;
  }
  if (namespace.length() == 0) {
    return "Namespace should be specified";
  }
  boolean duplicateName=(repo == null ? false : new ModelAccessHelper(repo).runReadAction(new Computable<Boolean>(){
    public Boolean compute(){
      return !(new ModuleRepositoryFacade(repo).getModulesByName(namespace).isEmpty());
    }
  }
));
  if (duplicateName) {
    return "Module namespace already exists";
  }
  if (NameUtil.shortNameFromLongName(namespace).length() == 0) {
    return "Enter valid namespace";
  }
  IFile moduleDir=getModuleFile(namespace,rootPath,extension).getParent();
  if (moduleDir.findChild(Language.LANGUAGE_MODELS).exists() || moduleDir.findChild(Language.LEGACY_LANGUAGE_MODELS).exists() || moduleDir.findChild(Solution.SOLUTION_MODELS).exists()) {
    return "Module already exists in this folder";
  }
  return null;
}

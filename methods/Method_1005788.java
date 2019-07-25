/** 
 * generate  {@code BUCKFile} 
 */
public static void generate(Project project,BuckFileManager buckFileManager,VisibilityExtension visibilityExtension){
  List<Rule> rules=createRules(project);
  File moduleDir=project.getBuildFile().getParentFile();
  File visibilityFile=new File(moduleDir,visibilityExtension.visibilityFileName);
  boolean hasVisibilityFile=visibilityFile.isFile();
  Multimap<String,String> extraLoadStatements=TreeMultimap.create();
  if (hasVisibilityFile) {
    rules.forEach(rule -> rule.fileConfiguredVisibility(true));
    extraLoadStatements.put(":" + visibilityExtension.visibilityFileName,visibilityExtension.visibilityFunction);
  }
  File buckFile=project.file(OkBuckGradlePlugin.BUCK);
  buckFileManager.writeToBuckFile(rules,buckFile,extraLoadStatements);
}

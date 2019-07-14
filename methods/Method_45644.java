/** 
 * parse module load config
 * @param moduleLoadList alias of all config modules
 * @param moduleName     the module name
 * @return need load
 */
static boolean needLoad(String moduleLoadList,String moduleName){
  String[] activatedModules=StringUtils.splitWithCommaOrSemicolon(moduleLoadList);
  boolean match=false;
  for (  String activatedModule : activatedModules) {
    if (StringUtils.ALL.equals(activatedModule)) {
      match=true;
    }
 else     if (activatedModule.equals(moduleName)) {
      match=true;
    }
 else     if (match && (activatedModule.equals("!" + moduleName) || activatedModule.equals("-" + moduleName))) {
      match=false;
      break;
    }
  }
  return match;
}

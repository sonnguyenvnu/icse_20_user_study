/** 
 * Registers new path alias.
 */
public void registerPathAlias(final String alias,final String path){
  final String existing=pathAliases.put(alias,path);
  if (existing != null) {
    throw new MadvocException("Duplicated alias detected: [" + alias + "] for paths: " + path + ", " + existing);
  }
}

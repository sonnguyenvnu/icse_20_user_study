/** 
 * Resolves file path depending on  {@link Match matching type}Returned path is formatted in unix style.
 */
protected String getMatchingFilePath(final File file){
  String path=null;
switch (matchType) {
case FULL_PATH:
    path=file.getAbsolutePath();
  break;
case RELATIVE_PATH:
path=file.getAbsolutePath();
path=path.substring(rootPath.length());
break;
case NAME:
path=file.getName();
}
path=FileNameUtil.separatorsToUnix(path);
return path;
}

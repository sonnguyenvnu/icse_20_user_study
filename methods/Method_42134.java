private static File getTargetFile(File source,File targetDir){
  File file=new File(targetDir,source.getName());
  if (!source.getParentFile().equals(targetDir) && !file.exists())   return file;
  return new File(targetDir,StringUtils.incrementFileNameSuffix(source.getName()));
}

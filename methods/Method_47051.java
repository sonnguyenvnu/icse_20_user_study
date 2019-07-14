public String getReadablePath(String path){
  if (isSftp())   return parseSftpPath(path);
  if (isSmb())   return parseSmbPath(path);
  return path;
}

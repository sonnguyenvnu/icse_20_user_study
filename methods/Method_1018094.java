@Override public boolean contains(String secretId){
  List<String> list=vaultTemplate.list(rootPath);
  return list.contains(secretId + "/");
}

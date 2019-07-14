@NonNull private String modifyQueryForFileSearch(@NonNull String query,boolean inPath){
  return query + "+" + "in:" + (inPath ? "path" : "" + "file") + "+" + "repo:" + login + "/" + repoId;
}

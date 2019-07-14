public void setFiles(@NonNull String ref,@NonNull String path,@NonNull List<RepoFile> paths){
  files.put(ref + "/" + path,paths);
}

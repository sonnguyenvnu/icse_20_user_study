@NonNull public static List<CommitFileChanges> construct(@Nullable List<CommitFileModel> files){
  if (files == null || files.isEmpty()) {
    return new ArrayList<>();
  }
  return Stream.of(files).map(CommitFileChanges::getCommitFileChanges).toList();
}

public static Single<Optional<Intent>> load(Context context,String repoOwner,String repoName,String commitSha,IntentUtils.InitialCommentMarker marker){
  RepositoryCommitService commitService=ServiceFactory.get(RepositoryCommitService.class,false);
  RepositoryCommentService commentService=ServiceFactory.get(RepositoryCommentService.class,false);
  Single<Commit> commitSingle=commitService.getCommit(repoOwner,repoName,commitSha).map(ApiHelpers::throwOnFailure);
  Single<List<GitComment>> commentSingle=ApiHelpers.PageIterator.toSingle(page -> commentService.getCommitComments(repoOwner,repoName,commitSha,page)).cache();
  Single<Optional<GitHubFile>> fileSingle=commentSingle.compose(RxUtils.filterAndMapToFirst(c -> marker.matches(c.id(),c.createdAt()))).zipWith(commitSingle,(comment,commit) -> {
    if (comment.isPresent()) {
      for (      GitHubFile commitFile : commit.files()) {
        if (commitFile.filename().equals(comment.get().path())) {
          return Optional.of(commitFile);
        }
      }
    }
    return Optional.absent();
  }
);
  return Single.zip(commitSingle,commentSingle,fileSingle,(commit,comments,fileOpt) -> {
    GitHubFile file=fileOpt.orNull();
    if (file != null && !FileUtils.isImage(file.filename())) {
      return Optional.of(CommitDiffViewerActivity.makeIntent(context,repoOwner,repoName,commitSha,file.filename(),file.patch(),comments,-1,-1,false,marker));
    }
 else     if (file == null) {
      return Optional.of(CommitActivity.makeIntent(context,repoOwner,repoName,commitSha,marker));
    }
    return Optional.absent();
  }
);
}

public static Single<Optional<Intent>> load(Context context,String repoOwner,String repoName,int pullRequestNumber,IntentUtils.InitialCommentMarker marker){
  final PullRequestReviewService reviewService=ServiceFactory.get(PullRequestReviewService.class,false);
  final PullRequestReviewCommentService commentService=ServiceFactory.get(PullRequestReviewCommentService.class,false);
  return ApiHelpers.PageIterator.toSingle(page -> commentService.getPullRequestComments(repoOwner,repoName,pullRequestNumber,page)).compose(RxUtils.sortList(ApiHelpers.COMMENT_COMPARATOR)).flatMap(comments -> {
    Map<String,ReviewComment> commentsByDiffHunkId=new HashMap<>();
    for (    ReviewComment comment : comments) {
      String id=TimelineItem.Diff.getDiffHunkId(comment);
      if (!commentsByDiffHunkId.containsKey(id)) {
        commentsByDiffHunkId.put(id,comment);
      }
      if (marker.matches(comment.id(),null)) {
        ReviewComment initialComment=commentsByDiffHunkId.get(id);
        long reviewId=initialComment.pullRequestReviewId();
        return reviewService.getReview(repoOwner,repoName,pullRequestNumber,reviewId).map(ApiHelpers::throwOnFailure).map(Optional::of);
      }
    }
    return Single.just(Optional.<Review>absent());
  }
).map(reviewOpt -> reviewOpt.map(review -> ReviewActivity.makeIntent(context,repoOwner,repoName,pullRequestNumber,review,marker)));
}

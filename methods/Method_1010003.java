private Single<Optional<Pair<String,String>>> resolve() throws ApiRequestException {
  if (mRefAndPath.startsWith("HEAD")) {
    return Single.just(Optional.of(Pair.create("HEAD",mRefAndPath.startsWith("HEAD/") ? mRefAndPath.substring(5) : null)));
  }
  final RepositoryBranchService branchService=ServiceFactory.get(RepositoryBranchService.class,false);
  final RepositoryService repoService=ServiceFactory.get(RepositoryService.class,false);
  return ApiHelpers.PageIterator.toSingle(page -> branchService.getBranches(mRepoOwner,mRepoName,page)).compose(this::matchBranch).flatMap(result -> result.orOptionalSingle(() -> ApiHelpers.PageIterator.toSingle(page -> repoService.getTags(mRepoOwner,mRepoName,page)).compose(this::matchBranch))).map(resultOpt -> resultOpt.orOptional(() -> {
    int slashPos=mRefAndPath.indexOf('/');
    String potentialSha=slashPos > 0 ? mRefAndPath.substring(0,slashPos) : mRefAndPath;
    if (SHA1_PATTERN.matcher(potentialSha).matches()) {
      return Optional.of(Pair.create(potentialSha,slashPos > 0 ? mRefAndPath.substring(slashPos + 1) : ""));
    }
    return Optional.absent();
  }
));
}

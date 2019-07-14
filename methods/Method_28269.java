@NonNull @Override public SpannableBuilder getMergeBy(@NonNull PullRequest pullRequest,@NonNull Context context){
  return PullRequest.getMergeBy(pullRequest,context,false);
}

private Observable<String> toastMessages(){
  return viewModel.outputs.showPostCommentErrorToast().map(ObjectUtils.coalesceWith(this.postCommentErrorString)).mergeWith(this.viewModel.outputs.showCommentPostedToast().map(__ -> this.commentPostedString));
}

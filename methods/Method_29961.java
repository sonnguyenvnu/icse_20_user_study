public static void show(Comment comment,boolean canReplyTo,boolean canDelete,Fragment fragment){
  CommentActionDialogFragment.newInstance(comment,canReplyTo,canDelete).show(fragment.getChildFragmentManager(),null);
}

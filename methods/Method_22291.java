@NonNull protected View buildCustomView(@Nullable Bundle savedInstanceState){
  final ScrollView root=new ScrollView(this);
  root.setPadding(PADDING,PADDING,PADDING,PADDING);
  root.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
  root.setFocusable(true);
  root.setFocusableInTouchMode(true);
  root.addView(scrollable);
  addViewToDialog(getMainView());
  final View comment=getCommentLabel();
  if (comment != null) {
    comment.setPadding(comment.getPaddingLeft(),PADDING,comment.getPaddingRight(),comment.getPaddingBottom());
    addViewToDialog(comment);
    String savedComment=null;
    if (savedInstanceState != null) {
      savedComment=savedInstanceState.getString(STATE_COMMENT);
    }
    userCommentView=getCommentPrompt(savedComment);
    addViewToDialog(userCommentView);
  }
  final View email=getEmailLabel();
  if (email != null) {
    email.setPadding(email.getPaddingLeft(),PADDING,email.getPaddingRight(),email.getPaddingBottom());
    addViewToDialog(email);
    String savedEmail=null;
    if (savedInstanceState != null) {
      savedEmail=savedInstanceState.getString(STATE_EMAIL);
    }
    userEmailView=getEmailPrompt(savedEmail);
    addViewToDialog(userEmailView);
  }
  return root;
}

public void onBind(){
  final Context context=context();
  this.creatorLabelTextView.setVisibility(View.GONE);
  this.userLabelTextView.setVisibility(View.GONE);
  if (CommentUtils.isUserAuthor(this.comment,this.project.creator())) {
    this.creatorLabelTextView.setVisibility(View.VISIBLE);
  }
 else   if (CommentUtils.isUserAuthor(this.comment,this.currentUser.getUser())) {
    this.userLabelTextView.setVisibility(View.VISIBLE);
  }
  Picasso.with(context).load(this.comment.author().avatar().small()).transform(new CircleTransformation()).into(this.avatarImageView);
  this.nameTextView.setText(this.comment.author().name());
  this.postDateTextView.setText(DateTimeUtils.relative(context,this.ksString,this.comment.createdAt()));
  if (CommentUtils.isDeleted(this.comment)) {
    this.commentBodyTextView.setTextColor(this.textSecondaryColor);
    this.commentBodyTextView.setTypeface(this.commentBodyTextView.getTypeface(),Typeface.ITALIC);
  }
 else {
    this.commentBodyTextView.setTextColor(this.textPrimaryColor);
    this.commentBodyTextView.setTypeface(this.commentBodyTextView.getTypeface(),Typeface.NORMAL);
  }
  this.commentBodyTextView.setText(this.comment.body());
}

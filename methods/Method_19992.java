public void bindToPost(Post post,View.OnClickListener starClickListener){
  titleView.setText(post.title);
  authorView.setText(post.author);
  numStarsView.setText(String.valueOf(post.starCount));
  bodyView.setText(post.body);
  starView.setOnClickListener(starClickListener);
}

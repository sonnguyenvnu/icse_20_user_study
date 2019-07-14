private void bindAuthorHolder(RecyclerView.ViewHolder holder,Book book){
  AuthorHolder authorHolder=(AuthorHolder)holder;
  authorHolder.introductionText.setText(book.authorIntroduction);
  authorHolder.itemView.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(WebViewActivity.makeIntent(book.url,true,context));
  }
);
}

private void bindTableOfContentsHolder(RecyclerView.ViewHolder holder,Book book){
  TableOfContentsHolder tableOfContentsHolder=(TableOfContentsHolder)holder;
  tableOfContentsHolder.tableOfContentsText.setText(book.tableOfContents);
  tableOfContentsHolder.itemView.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(TableOfContentsActivity.makeIntent(book,context));
  }
);
}

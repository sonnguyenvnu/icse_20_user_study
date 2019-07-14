@Override protected void bindIntroductionHolder(RecyclerView.ViewHolder holder,Book book){
  super.bindIntroductionHolder(holder,book);
  IntroductionHolder introductionHolder=(IntroductionHolder)holder;
  introductionHolder.introductionLayout.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemIntroductionActivity.makeIntent(book,context));
  }
);
}

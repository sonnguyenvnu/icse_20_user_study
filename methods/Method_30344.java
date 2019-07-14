@Override protected void bindIntroductionHolder(RecyclerView.ViewHolder holder,Movie movie){
  super.bindIntroductionHolder(holder,movie);
  IntroductionHolder introductionHolder=(IntroductionHolder)holder;
  introductionHolder.introductionLayout.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemIntroductionActivity.makeIntent(movie,context));
  }
);
}

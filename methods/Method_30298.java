@Override protected void bindIntroductionHolder(RecyclerView.ViewHolder holder,Game game){
  super.bindIntroductionHolder(holder,game);
  IntroductionHolder introductionHolder=(IntroductionHolder)holder;
  introductionHolder.introductionLayout.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemIntroductionActivity.makeIntent(game,context));
  }
);
}

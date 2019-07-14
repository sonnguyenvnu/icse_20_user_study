@Override protected void bindIntroductionHolder(RecyclerView.ViewHolder holder,Music music){
  super.bindIntroductionHolder(holder,music);
  IntroductionHolder introductionHolder=(IntroductionHolder)holder;
  introductionHolder.introductionLayout.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemIntroductionActivity.makeIntent(music,context));
  }
);
}

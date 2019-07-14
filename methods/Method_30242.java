protected void bindIntroductionHolder(RecyclerView.ViewHolder holder,T item){
  IntroductionHolder introductionHolder=(IntroductionHolder)holder;
  introductionHolder.introductionText.setText(!TextUtils.isEmpty(item.introduction) ? item.introduction : introductionHolder.introductionText.getContext().getString(R.string.item_introduction_empty));
}

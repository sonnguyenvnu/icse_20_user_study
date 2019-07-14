public void setTop250(Honor honor){
  boolean isTop250=honor != null;
  ViewUtils.setVisibleOrGone(mTop250Layout,isTop250);
  if (!isTop250) {
    return;
  }
  final Context context=getContext();
  mTop250RankText.setText(context.getString(R.string.item_top_250_rank_format,honor.rank));
  mTop250Layout.setOnClickListener(view -> {
    UriHandler.open("https://movie.douban.com/top250",context);
  }
);
}

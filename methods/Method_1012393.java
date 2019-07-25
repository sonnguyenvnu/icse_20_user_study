@Override public void convert(RecommendLiveViewHolder holder,RecommendLiveBean.RoomBean data){
  holder.setOnRecyclerViewItemClickListener(new RecommendLiveViewHolder.OnRecyclerViewItemClickListener(){
    @Override public void onItemClick(    View view,    int position,    RecommendLiveBean.RoomBean.ListBean bean){
      Intent intent=new Intent(view.getContext(),VideoActivity.class);
      intent.putExtra(LiveUtil.UID,bean.getUid() + "");
      intent.putExtra(LiveUtil.IS_FULL,LiveUtil.SHOWING.equalsIgnoreCase(bean.getCategory_slug()));
      intent.putExtra(LiveUtil.THUMB,bean.getThumb());
      view.getContext().startActivity(intent);
    }
  }
).setData(data.getList()).setText(R.id.tv_item_fragment_recommend_live_title,data.getName()).setImageUrl(R.id.iv_item_fragment_recommend_live_icon,data.getIcon(),R.drawable.default_recommend_icon,R.drawable.default_recommend_icon).setOnClickListener(R.id.tv_item_fragment_recommend_live_more,new View.OnClickListener(){
    @Override public void onClick(    View v){
    }
  }
);
}

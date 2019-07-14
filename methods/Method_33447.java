@Override protected void convert(JViewHolder<ItemCategoryArticleBinding> helper,ArticlesBean bean){
  if (bean != null) {
    helper.binding.setBean(bean);
    helper.binding.executePendingBindings();
    helper.binding.vbCollect.setOnClickListener(new PerfectClickListener(){
      @Override protected void onNoDoubleClick(      View v){
        if (UserUtil.isLogin(activity) && model != null) {
          if (!helper.binding.vbCollect.isChecked()) {
            model.unCollect(false,bean.getId(),bean.getOriginId(),new WanNavigator.OnCollectNavigator(){
              @Override public void onSuccess(){
                bean.setCollect(helper.binding.vbCollect.isChecked());
                ToastUtil.showToastLong("?????");
              }
              @Override public void onFailure(){
                bean.setCollect(true);
                notifyItemChanged(helper.getAdapterPosition());
                ToastUtil.showToastLong("??????");
              }
            }
);
          }
 else {
            model.collect(bean.getId(),new WanNavigator.OnCollectNavigator(){
              @Override public void onSuccess(){
                bean.setCollect(true);
                ToastUtil.showToastLong("????");
              }
              @Override public void onFailure(){
                ToastUtil.showToastLong("????");
                bean.setCollect(false);
                notifyItemChanged(helper.getAdapterPosition());
              }
            }
);
          }
        }
 else {
          bean.setCollect(false);
          notifyItemChanged(helper.getAdapterPosition());
        }
      }
    }
);
  }
}

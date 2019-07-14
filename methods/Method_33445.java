@Override protected void convert(JViewHolder<ItemBookTypeBinding> helper,String bean){
  if (bean != null) {
    helper.binding.setName(bean);
    if (!TextUtils.isEmpty(type)) {
      if (!TextUtils.isEmpty(bean) && bean.equals(type)) {
        helper.binding.tvTitle.setTextColor(CommonUtils.getColor(R.color.colorTheme));
      }
 else {
        helper.binding.tvTitle.setTextColor(CommonUtils.getColor(R.color.select_navi_text));
      }
    }
    helper.binding.tvTitle.setOnClickListener(v -> {
      if (listener != null) {
        if (!TextUtils.isEmpty(bean)) {
          listener.onSelected(bean);
        }
      }
    }
);
  }
}

@Override protected void convert(BaseWrappedViewHolder holder,String data){
  holder.setText(R.id.tv_item_list_content,data).setOnItemClickListener();
}

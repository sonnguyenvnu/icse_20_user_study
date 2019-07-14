@Override public void onItemClick(int position,View v,String item){
  if (getView() != null) {
    getView().onColorSelected(item);
  }
}

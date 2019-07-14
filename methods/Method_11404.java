@Override protected void convert(BaseViewHolder holder,ModelContactCity item){
  if (holder instanceof CityHolder) {
    ((CityHolder)holder).city_name.setText(item.name);
  }
 else {
    String letter=item.pys.substring(0,1);
    ((PinnedHolder)holder).city_tip.setText(letter);
  }
}

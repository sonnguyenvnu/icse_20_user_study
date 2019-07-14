/** 
 * ??View
 * @return
 */
@SuppressLint("InflateParams") @Override public View createView(LayoutInflater inflater){
  this.inflater=inflater;
  convertView=inflater.inflate(R.layout.bottom_menu_view,null);
  llBottomMenuViewMainItemContainer=(LinearLayout)convertView.findViewById(R.id.llBottomMenuViewMainItemContainer);
  return convertView;
}

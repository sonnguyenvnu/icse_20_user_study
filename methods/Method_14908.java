@Override public MomentView createView(int position,ViewGroup parent){
  final MomentView itemView=new MomentView(context,resources);
  itemView.setOnDataChangedListener(new OnDataChangedListener(){
    @Override public void onDataChanged(){
      if (list == null) {
        return;
      }
      MomentItem data=itemView.getData();
      if (data == null) {
        if (itemView.getPosition() >= 0 && itemView.getPosition() < list.size()) {
          list.remove(itemView.getPosition());
          notifyDataSetChanged();
        }
      }
 else {
        list.set(itemView.getPosition(),data);
        itemView.bindView(data);
      }
    }
  }
);
  return itemView;
}

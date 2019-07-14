private void initRulerView(){
  mWheelview=findViewById(R.id.wheelview);
  mWheelview2=findViewById(R.id.wheelview2);
  mWheelview3=findViewById(R.id.wheelview3);
  mWheelview4=findViewById(R.id.wheelview4);
  mWheelview5=findViewById(R.id.wheelview5);
  mSelectedTv=findViewById(R.id.selected_tv);
  mChangedTv=findViewById(R.id.changed_tv);
  final List<String> items=new ArrayList<>();
  for (int i=1; i <= 40; i++) {
    items.add(String.valueOf(i * 1000));
  }
  mWheelview.setItems(items);
  mWheelview.selectIndex(8);
  mWheelview.setAdditionCenterMark("?");
  List<String> items2=new ArrayList<>();
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("??");
  items2.add("???");
  items2.add("???");
  mWheelview2.setItems(items2);
  List<String> items3=new ArrayList<>();
  items3.add("1");
  items3.add("2");
  items3.add("3");
  items3.add("5");
  items3.add("7");
  items3.add("11");
  items3.add("13");
  items3.add("17");
  items3.add("19");
  items3.add("23");
  items3.add("29");
  items3.add("31");
  mWheelview3.setItems(items3);
  mWheelview3.setAdditionCenterMark("m");
  mWheelview5.setItems(items);
  mWheelview5.setMinSelectableIndex(3);
  mWheelview5.setMaxSelectableIndex(items.size() - 3);
  items.remove(items.size() - 1);
  items.remove(items.size() - 2);
  items.remove(items.size() - 3);
  items.remove(items.size() - 4);
  mSelectedTv.setText(String.format("onWheelItemSelected?%1$s",""));
  mChangedTv.setText(String.format("onWheelItemChanged?%1$s",""));
  mWheelview5.setOnWheelItemSelectedListener(new RxRulerWheelView.OnWheelItemSelectedListener(){
    @Override public void onWheelItemSelected(    RxRulerWheelView wheelView,    int position){
      mSelectedTv.setText(String.format("onWheelItemSelected?%1$s",wheelView.getItems().get(position)));
    }
    @Override public void onWheelItemChanged(    RxRulerWheelView wheelView,    int position){
      mChangedTv.setText(String.format("onWheelItemChanged?%1$s",wheelView.getItems().get(position)));
    }
  }
);
  mWheelview4.postDelayed(new Runnable(){
    @Override public void run(){
      mWheelview4.setItems(items);
    }
  }
,3000);
}

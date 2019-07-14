@Override public void initEvent(){
  findViewById(R.id.llSelectPictureBg).setOnClickListener(this);
  toActivity(new Intent(context,BottomMenuWindow.class).putExtra(BottomMenuWindow.INTENT_TITLE,"????").putExtra(BottomMenuWindow.INTENT_ITEMS,new String[]{"??","??"}),REQUEST_TO_BOTTOM_MENU,false);
}

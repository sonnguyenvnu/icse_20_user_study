@Override public void initEvent(){
  for (int i=0; i < vTabClickViews.length; i++) {
    final int which=i;
    vTabClickViews[which].setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        selectFragment(which);
      }
    }
);
  }
}

@SuppressLint("InflateParams") @Override public View getLeftMenu(Activity activity){
  if (leftMenu == null) {
    leftMenu=(TextView)LayoutInflater.from(activity).inflate(R.layout.top_right_tv,null);
    leftMenu.setGravity(Gravity.CENTER);
    leftMenu.setText("??");
    leftMenu.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        onDragBottom(false);
      }
    }
);
  }
  return leftMenu;
}

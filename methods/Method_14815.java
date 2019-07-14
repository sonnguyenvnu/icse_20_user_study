@SuppressLint("InflateParams") @Override public View getRightMenu(Activity activity){
  if (rightMenu == null) {
    rightMenu=(TextView)LayoutInflater.from(activity).inflate(R.layout.top_right_tv,null);
    rightMenu.setGravity(Gravity.CENTER);
    rightMenu.setText("??");
    rightMenu.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        onDragBottom(true);
      }
    }
);
  }
  return rightMenu;
}

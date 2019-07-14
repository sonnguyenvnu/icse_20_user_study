@SuppressLint("InflateParams") @Override public View getRightMenu(Activity activity){
  if (rightMenu == null) {
    rightMenu=(ImageView)LayoutInflater.from(activity).inflate(R.layout.top_right_iv,null);
    rightMenu.setImageResource(R.drawable.search);
    rightMenu.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        onDragBottom(true);
      }
    }
);
  }
  return rightMenu;
}

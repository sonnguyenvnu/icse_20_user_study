@SuppressLint("InflateParams") @Override public View getLeftMenu(Activity activity){
  if (leftMenu == null) {
    leftMenu=(ImageView)LayoutInflater.from(activity).inflate(R.layout.top_right_iv,null);
    leftMenu.setImageResource(R.drawable.add);
    leftMenu.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        onDragBottom(false);
      }
    }
);
  }
  return leftMenu;
}

private void setClickAnimator(final ViewHolder holder,final int position){
  setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      if (mSelectPosition == DEFAULT_SELECT_POSITION) {
        return;
      }
      performItemClick(mViewHolders.get(mSelectPosition));
    }
  }
);
  holder.itemView.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      performItemClick(holder);
    }
  }
);
}

public void setArguments(int titleResId,int columns,int size){
  Bundle bundle=new Bundle();
  bundle.putInt(KEY_TITLE_ID,titleResId);
  bundle.putInt(KEY_COLUMNS,columns);
  bundle.putInt(KEY_SIZE,size);
  setArguments(bundle);
}

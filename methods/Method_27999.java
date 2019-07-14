@Override public boolean canPressBack(){
  return adapter == null || adapter.getItemCount() == 0;
}

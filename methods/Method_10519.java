@Override public void onLeftItemSelected(int position,ModelDishMenu menu){
  int sum=0;
  for (int i=0; i < position; i++) {
    sum+=mModelDishMenuList.get(i).getModelDishList().size() + 1;
  }
  LinearLayoutManager layoutManager=(LinearLayoutManager)mRightMenu.getLayoutManager();
  layoutManager.scrollToPositionWithOffset(sum,0);
  leftClickType=true;
}

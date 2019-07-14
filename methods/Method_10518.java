private void showHeadView(){
  headerLayout.setTranslationY(0);
  View underView=mRightMenu.findChildViewUnder(headerView.getX(),0);
  if (underView != null && underView.getContentDescription() != null) {
    int position=Integer.parseInt(underView.getContentDescription().toString());
    ModelDishMenu menu=rightAdapter.getMenuOfMenuByPosition(position + 1);
    headMenu=menu;
    headerView.setText(headMenu.getMenuName());
    for (int i=0; i < mModelDishMenuList.size(); i++) {
      if (mModelDishMenuList.get(i) == headMenu) {
        leftAdapter.setSelectedNum(i);
        break;
      }
    }
  }
}

@Override public int getItemViewType(int position){
  int sum=0;
  for (  ModelDishMenu menu : mMenuList) {
    if (position == sum) {
      return MENU_TYPE;
    }
    sum+=menu.getModelDishList().size() + 1;
  }
  return DISH_TYPE;
}

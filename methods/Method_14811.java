@Override protected void selectTab(int position){
  tvBaseTitle.setText(TAB_NAMES[position]);
  View left=null, right=null;
  if (fragments[position] instanceof TopBarMenuCallback) {
    left=((TopBarMenuCallback)fragments[position]).getLeftMenu(context);
    right=((TopBarMenuCallback)fragments[position]).getRightMenu(context);
  }
  llMainTabLeftContainer.removeAllViews();
  if (left != null) {
    llMainTabLeftContainer.addView(left);
  }
  llMainTabRightContainer.removeAllViews();
  if (right != null) {
    llMainTabRightContainer.addView(right);
  }
  verifyLogin();
}

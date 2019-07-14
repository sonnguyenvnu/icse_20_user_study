private void initHeadView(){
  headMenu=rightAdapter.getMenuOfMenuByPosition(0);
  headerLayout.setContentDescription("0");
  headerView.setText(headMenu.getMenuName());
}

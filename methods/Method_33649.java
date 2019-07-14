private void load(){
  int tabPosition=binding.tlSearch.getSelectedTabPosition();
switch (tabPosition) {
case 0:
    viewModel.setPage(0);
  loadWanData();
break;
case 1:
viewModel.setType("Android");
viewModel.setGankPage(1);
loadGankData();
break;
case 2:
viewModel.setType("all");
viewModel.setGankPage(1);
loadGankData();
break;
case 3:
break;
default :
break;
}
}

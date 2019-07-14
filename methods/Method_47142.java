public void onSearchPreExecute(String query){
  getMainActivity().getAppbar().getBottomBar().setPathText("");
  getMainActivity().getAppbar().getBottomBar().setFullPathText(getString(R.string.searching,query));
}

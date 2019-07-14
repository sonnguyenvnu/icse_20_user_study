private void setAdapter(HotMovieBean hotMovieBean){
  oneAdapter.clear();
  oneAdapter.addAll(hotMovieBean.getSubjects());
  oneAdapter.notifyDataSetChanged();
  bindingView.listOne.refreshComplete();
  if (isFirst) {
    isFirst=false;
  }
}

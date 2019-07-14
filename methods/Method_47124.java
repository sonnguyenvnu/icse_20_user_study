void switchToGrid(){
  IS_LIST=false;
  if (utilsProvider.getAppTheme().equals(AppTheme.LIGHT)) {
    listView.setBackgroundColor(Utils.getColor(getContext(),R.color.grid_background_light));
  }
  if (mLayoutManagerGrid == null)   if (columns == -1 || columns == 0)   mLayoutManagerGrid=new GridLayoutManager(getActivity(),3);
 else   mLayoutManagerGrid=new GridLayoutManager(getActivity(),columns);
  setGridLayoutSpanSizeLookup(mLayoutManagerGrid);
  listView.setLayoutManager(mLayoutManagerGrid);
  listView.clearOnScrollListeners();
  adapter=null;
}

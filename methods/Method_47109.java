private void createViews(List<CompressedObjectParcelable> items,String dir){
  if (compressedExplorerAdapter == null) {
    compressedExplorerAdapter=new CompressedExplorerAdapter(getActivity(),utilsProvider,items,this,decompressor,PreferenceManager.getDefaultSharedPreferences(getActivity()));
    listView.setAdapter(compressedExplorerAdapter);
  }
 else {
    compressedExplorerAdapter.generateZip(items);
  }
  folder=0;
  file=0;
  for (  CompressedObjectParcelable item : items) {
    if (item.type == CompressedObjectParcelable.TYPE_GOBACK)     continue;
    if (item.directory)     folder++;
 else     file++;
  }
  stopAnims=true;
  if (!addheader) {
    listView.removeItemDecoration(dividerItemDecoration);
    addheader=true;
  }
 else {
    dividerItemDecoration=new DividerItemDecoration(getActivity(),true,showDividers);
    listView.addItemDecoration(dividerItemDecoration);
    addheader=false;
  }
  final FastScroller fastScroller=rootView.findViewById(R.id.fastscroll);
  fastScroller.setRecyclerView(listView,1);
  fastScroller.setPressedHandleColor(mainActivity.getAccent());
  ((AppBarLayout)mToolbarContainer).addOnOffsetChangedListener((appBarLayout,verticalOffset) -> {
    fastScroller.updateHandlePosition(verticalOffset,112);
  }
);
  listView.stopScroll();
  relativeDirectory=dir;
  updateBottomBar();
  swipeRefreshLayout.setRefreshing(false);
}

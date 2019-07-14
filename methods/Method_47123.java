void setGridLayoutSpanSizeLookup(GridLayoutManager mLayoutManagerGrid){
  mLayoutManagerGrid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
    @Override public int getSpanSize(    int position){
switch (adapter.getItemViewType(position)) {
case RecyclerAdapter.TYPE_HEADER_FILES:
case RecyclerAdapter.TYPE_HEADER_FOLDERS:
        return columns;
default :
      return 1;
  }
}
}
);
}

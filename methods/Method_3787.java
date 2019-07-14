protected void assignSpans(RecyclerView.Recycler recycler,RecyclerView.State state,int count,boolean layingOutInPrimaryDirection){
  int span, start, end, diff;
  if (layingOutInPrimaryDirection) {
    start=0;
    end=count;
    diff=1;
  }
 else {
    start=count - 1;
    end=-1;
    diff=-1;
  }
  span=0;
  for (int i=start; i != end; i+=diff) {
    View view=mSet[i];
    LayoutParams params=(LayoutParams)view.getLayoutParams();
    params.mSpanSize=getSpanSize(recycler,state,getPosition(view));
    params.mSpanIndex=span;
    span+=params.mSpanSize;
  }
}

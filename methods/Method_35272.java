@Override protected void onViewBound(@NonNull View view){
  ((ElasticDragDismissFrameLayout)view).addListener(dragDismissListener);
}

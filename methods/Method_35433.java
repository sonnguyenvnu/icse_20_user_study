@Override public void onShow(DialogInterface dialog){
  resizeDialogSize();
  if (recyclerView == null) {
    recyclerView=(RecyclerView)getDialog().findViewById(R.id.recycler_view);
    recyclerView.setAdapter(mAdapter);
    recyclerView.addItemDecoration(new DefaultDividerDecoration());
  }
}

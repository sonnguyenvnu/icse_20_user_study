@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  ArrayList<O> objects=getArguments().getParcelableArrayList(BundleConstant.ITEM);
  String titleText=getArguments().getString(BundleConstant.EXTRA);
  title.setText(titleText);
  if (objects != null) {
    SimpleListAdapter<O> adapter=new SimpleListAdapter<>(objects,this);
    recycler.addDivider();
    recycler.setAdapter(adapter);
  }
 else {
    dismiss();
  }
  fastScroller.attachRecyclerView(recycler);
}

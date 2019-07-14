@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  final RecyclerBinder recyclerBinder=new RecyclerBinder.Builder().layoutInfo(new LinearLayoutInfo(c,OrientationHelper.VERTICAL,false)).build(c);
  for (int i=0; i < 32; i++) {
    recyclerBinder.insertItemAt(i,LearningPropsComponent.create(c).text1("Item: " + i).text2("Item: " + i).build());
  }
  return Recycler.create(c).binder(recyclerBinder).build();
}

@Override public SortedSetDocValues ordinals(ValuesHolder values){
  return (SortedSetDocValues)DocValues.singleton(new Docs(this,values));
}

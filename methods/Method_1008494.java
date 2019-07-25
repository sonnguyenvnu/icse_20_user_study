@Override public SortedSetDocValues ordinals(ValuesHolder values){
  if (multiValued) {
    return new MultiDocs(this,values);
  }
 else {
    return (SortedSetDocValues)DocValues.singleton(new SingleDocs(this,values));
  }
}

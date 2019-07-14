@Override protected final ArrayList<CompressedObjectParcelable> doInBackground(Void... voids){
  ArrayList<CompressedObjectParcelable> elements=new ArrayList<>();
  if (createBackItem)   elements.add(0,new CompressedObjectParcelable());
  addElements(elements);
  Collections.sort(elements,new CompressedObjectParcelable.Sorter());
  return elements;
}

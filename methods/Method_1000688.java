public LinkedIntArray push(int e){
  int[] array;
  int row=cursor / width;
  int i=cursor % width;
  if (cache.size() == 0 || (cursor != offset && i == 0)) {
    array=new int[width];
    cache.add(array);
  }
 else {
    array=cache.get(row);
  }
  array[i]=e;
  cursor++;
  return this;
}

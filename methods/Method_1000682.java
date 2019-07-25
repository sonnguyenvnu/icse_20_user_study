@SuppressWarnings("unchecked") public LinkedArray<T> push(T e){
  T[] array;
  int row=cursor / width;
  int i=cursor % width;
  if (cache.size() == 0 || (cursor != offset && i == 0)) {
    if (null == eleType)     array=(T[])Array.newInstance(e.getClass(),width);
 else     array=(T[])Array.newInstance(eleType,width);
    cache.add(array);
  }
 else {
    array=cache.get(row);
  }
  array[i]=e;
  cursor++;
  return this;
}

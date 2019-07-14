/** 
 * Returns an array of values as a union set of the two input arrays.
 * @param < T >
 * @param values
 * @param newValues
 * @return the union of the two arrays
 */
@Deprecated public static <T>T[] addWithoutDuplicates(T[] values,T[] newValues){
  Set<T> originals=new HashSet<>(values.length);
  for (  T value : values) {
    originals.add(value);
  }
  List<T> newOnes=new ArrayList<>(newValues.length);
  for (  T value : newValues) {
    if (originals.contains(value)) {
      continue;
    }
    newOnes.add(value);
  }
  T[] largerOne=(T[])Array.newInstance(values.getClass().getComponentType(),values.length + newOnes.size());
  System.arraycopy(values,0,largerOne,0,values.length);
  for (int i=values.length; i < largerOne.length; i++) {
    largerOne[i]=newOnes.get(i - values.length);
  }
  return largerOne;
}

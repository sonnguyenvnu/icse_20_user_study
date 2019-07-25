/** 
 * Returns data converted into list.
 * @param data data
 * @param < T >  data type
 * @return data list
 */
public static <T>ArrayList<T> copy(final T... data){
  final ArrayList<T> list=new ArrayList<T>(data.length);
  Collections.addAll(list,data);
  return list;
}

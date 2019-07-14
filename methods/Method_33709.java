/** 
 * ????ViewModel?class??
 */
public static <T>Class<T> getViewModel(Object obj){
  Class<?> currentClass=obj.getClass();
  Class<T> tClass=getGenericClass(currentClass,AndroidViewModel.class);
  if (tClass == null || tClass == AndroidViewModel.class || tClass == NoViewModel.class) {
    return null;
  }
  return tClass;
}

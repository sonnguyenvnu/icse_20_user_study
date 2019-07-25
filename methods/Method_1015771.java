public static <T>boolean contains(T key,T[] list){
  if (list == null)   return false;
  for (  T tmp : list)   if (tmp == key || tmp.equals(key))   return true;
  return false;
}

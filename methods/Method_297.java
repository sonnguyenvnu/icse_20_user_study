public static <T>T findOptionalViewAsType(View source,@IdRes int id,String who,Class<T> cls){
  View view=source.findViewById(id);
  return castView(view,id,who,cls);
}

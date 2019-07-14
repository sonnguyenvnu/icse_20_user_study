@Override public void onDeleted(@Nullable Context context,@Nullable int[] ids){
  if (context == null)   throw new RuntimeException("context is null");
  if (ids == null)   throw new RuntimeException("ids is null");
  updateDependencies(context);
  for (  int id : ids) {
    try {
      BaseWidget widget=getWidgetFromId(context,id);
      widget.delete();
    }
 catch (    HabitNotFoundException e) {
      e.printStackTrace();
    }
  }
}

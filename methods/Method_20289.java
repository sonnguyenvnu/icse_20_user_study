public CharSequence toString(Context context){
  if (formatArgs == null || formatArgs.length == 0) {
    return context.getResources().getQuantityString(id,quantity);
  }
 else {
    return context.getResources().getQuantityString(id,quantity,formatArgs);
  }
}

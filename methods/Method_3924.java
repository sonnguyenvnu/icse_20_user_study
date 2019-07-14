private String getFullClassName(Context context,String className){
  if (className.charAt(0) == '.') {
    return context.getPackageName() + className;
  }
  if (className.contains(".")) {
    return className;
  }
  return RecyclerView.class.getPackage().getName() + '.' + className;
}

public static View findRequiredView(View source,@IdRes int id,String who){
  View view=source.findViewById(id);
  if (view != null) {
    return view;
  }
  String name=getResourceEntryName(source,id);
  throw new IllegalStateException("Required view '" + name + "' with ID " + id + " for " + who + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional'" + " (methods) annotation.");
}

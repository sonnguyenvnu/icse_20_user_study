private static void unsetViewTags(View view,SparseArray<Object> viewTags){
  if (view instanceof ComponentHost) {
    final ComponentHost host=(ComponentHost)view;
    host.setViewTags(null);
  }
 else {
    if (viewTags != null) {
      for (int i=0, size=viewTags.size(); i < size; i++) {
        view.setTag(viewTags.keyAt(i),null);
      }
    }
  }
}

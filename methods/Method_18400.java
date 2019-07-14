private static void setViewTags(View view,SparseArray<Object> viewTags){
  if (viewTags == null) {
    return;
  }
  if (view instanceof ComponentHost) {
    final ComponentHost host=(ComponentHost)view;
    host.setViewTags(viewTags);
  }
 else {
    for (int i=0, size=viewTags.size(); i < size; i++) {
      view.setTag(viewTags.keyAt(i),viewTags.valueAt(i));
    }
  }
}

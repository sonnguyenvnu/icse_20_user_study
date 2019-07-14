public static void replaceChild(@NonNull ViewGroup viewGroup,@NonNull View oldChild,@NonNull View newChild){
  int index=viewGroup.indexOfChild(oldChild);
  viewGroup.removeViewAt(index);
  viewGroup.addView(newChild,index);
}

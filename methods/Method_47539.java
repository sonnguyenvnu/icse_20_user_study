public static void setupEditorAction(@NonNull ViewGroup parent,@NonNull TextView.OnEditorActionListener listener){
  for (int i=0; i < parent.getChildCount(); i++) {
    View child=parent.getChildAt(i);
    if (child instanceof ViewGroup)     setupEditorAction((ViewGroup)child,listener);
    if (child instanceof TextView)     ((TextView)child).setOnEditorActionListener(listener);
  }
}

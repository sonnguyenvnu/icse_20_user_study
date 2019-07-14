private static boolean isInteractive(View view){
  return MountState.getComponentClickListener(view) != null || MountState.getComponentLongClickListener(view) != null || MountState.getComponentTouchListener(view) != null;
}

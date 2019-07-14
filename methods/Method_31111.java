public static void setLayoutFullscreen(@NonNull View view){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
  }
}

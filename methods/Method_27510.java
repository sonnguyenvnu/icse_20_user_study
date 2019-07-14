private void setupToolbarAndStatusBar(@Nullable Toolbar toolbar){
  changeStatusBarColor(isTransparent());
  if (toolbar != null) {
    setSupportActionBar(toolbar);
    if (canBack()) {
      if (getSupportActionBar() != null) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (canBack()) {
          View navIcon=getToolbarNavigationIcon(toolbar);
          if (navIcon != null) {
            navIcon.setOnLongClickListener(v -> {
              Intent intent=new Intent(this,MainActivity.class);
              startActivity(intent);
              finish();
              return true;
            }
);
          }
        }
      }
    }
  }
}

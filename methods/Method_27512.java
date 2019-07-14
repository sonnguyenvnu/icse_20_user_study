protected void hideShowShadow(boolean show){
  if (appbar != null) {
    appbar.setElevation(show ? getResources().getDimension(R.dimen.spacing_micro) : 0.0f);
  }
}

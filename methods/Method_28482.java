private void initButton(@NonNull Bundle bundle){
  Bundle extra=bundle.getBundle("bundle");
  if (extra != null) {
    boolean yesNo=extra.getBoolean(BundleConstant.YES_NO_EXTRA);
    if (yesNo) {
      ok.setText(R.string.yes);
      cancel.setText(R.string.no);
    }
 else {
      boolean hideButtons=extra.getBoolean("hide_buttons");
      String primaryExtra=extra.getString("primary_extra");
      String secondaryExtra=extra.getString("secondary_extra");
      if (hideButtons) {
        ok.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
      }
 else       if (!InputHelper.isEmpty(primaryExtra)) {
        ok.setText(primaryExtra);
        if (!InputHelper.isEmpty(secondaryExtra))         cancel.setText(secondaryExtra);
        ok.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
      }
    }
  }
}

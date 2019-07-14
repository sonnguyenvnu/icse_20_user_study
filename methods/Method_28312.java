@OnItemSelected(R.id.mergeMethod) void onItemSelect(int position){
  if (position > 0) {
    if (!PrefGetter.isProEnabled()) {
      mergeMethod.setSelection(0);
      PremiumActivity.Companion.startActivity(getContext());
    }
  }
}

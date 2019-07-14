@Override public void onChooseColor(int position,int color){
  persistString(getContext().getResources().getStringArray(R.array.theme_colors)[position]);
  getOnPreferenceChangeListener().onPreferenceChange(this,getContext().getResources().getStringArray(R.array.theme_colors)[position]);
}

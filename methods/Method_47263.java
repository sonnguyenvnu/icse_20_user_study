private View.OnClickListener setListener(final View v,@IdRes int id,final int elem){
  final PathSwitchPreference t=this;
  View.OnClickListener l=view -> {
    lastItemClicked=elem;
    getOnPreferenceClickListener().onPreferenceClick(t);
  }
;
  v.findViewById(id).setOnClickListener(l);
  return l;
}

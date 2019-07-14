@OnClick({R.id.menu_button,R.id.filter_text_view}) protected void menuButtonClick(){
  final DiscoveryActivity activity=(DiscoveryActivity)getContext();
  activity.discoveryLayout().openDrawer(GravityCompat.START);
}

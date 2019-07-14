public void displayTimeline(Album album){
  unreferenceFragments();
  timelineFragment=TimelineFragment.Companion.newInstance(album);
  fragmentMode=FragmentMode.MODE_TIMELINE;
  getSupportFragmentManager().beginTransaction().replace(R.id.content,timelineFragment,TimelineFragment.TAG).addToBackStack(null).commit();
  setupUiForTimeline();
}

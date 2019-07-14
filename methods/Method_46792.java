public MainFragment getCurrentMainFragment(){
  TabFragment tab=getTabFragment();
  if (tab != null && tab.getCurrentTabFragment() instanceof MainFragment) {
    return (MainFragment)tab.getCurrentTabFragment();
  }
 else   return null;
}

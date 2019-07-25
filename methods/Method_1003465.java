private void init(){
  isPasswordAccepted=true;
  getFragmentManagerInstance();
  NavigationDrawerFragment mNavigationDrawerFragment=(NavigationDrawerFragment)getFragmentManagerInstance().findFragmentById(R.id.navigation_drawer);
  if (mNavigationDrawerFragment == null) {
    FragmentTransaction fragmentTransaction=getFragmentManagerInstance().beginTransaction();
    fragmentTransaction.replace(R.id.navigation_drawer,new NavigationDrawerFragment(),FRAGMENT_DRAWER_TAG).commit();
  }
  if (getFragmentManagerInstance().findFragmentByTag(FRAGMENT_LIST_TAG) == null) {
    FragmentTransaction fragmentTransaction=getFragmentManagerInstance().beginTransaction();
    fragmentTransaction.add(R.id.fragment_container,new ListFragment(),FRAGMENT_LIST_TAG).commit();
  }
  handleIntents();
}

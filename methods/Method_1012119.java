@Override protected FindUsagesOptions clone(){
  return new FindUsagesOptions(myFindersOptions,myScopeOptions,myViewOptions);
}

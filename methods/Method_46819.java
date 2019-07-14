/** 
 * This is a hack see  {@link PreferencesActivity#saveListViewState(int,Parcelable)}
 */
public Parcelable restoreListViewState(int prefFragment){
  return fragmentsListViewParcelables[prefFragment];
}

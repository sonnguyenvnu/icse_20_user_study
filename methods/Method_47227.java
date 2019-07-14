/** 
 * This is a hack, when the SavedState class is unmarshalled a "ClassNotFoundException" will be thrown (the actual class not found is "android.support.design.widget.NavigationView$SavedState") and I seem to only be able to replicate on Marshmallow (someone else replicated in N through O_MR1 see https://github.com/TeamAmaze/AmazeFileManager/issues/1400#issuecomment-413086603). Trying to find the class and returning false if Class.forName() throws "ClassNotFoundException" doesn't work because the class seems to have been loaded with the current loader (not the one the unmarshaller uses); of course I have no idea of what any of this means so I could be wrong. For the crash see https://github.com/TeamAmaze/AmazeFileManager/issues/1101.
 */
public boolean isNavigationViewSavedStateMissing(){
  return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT <= Build.VERSION_CODES.P;
}

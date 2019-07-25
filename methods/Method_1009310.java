/** 
 * Open editor and select/navigate to the object there if possible. Just do nothing if navigation is not possible like in case of a package
 * @param requestFocus <code>true</code> if focus requesting is necessary
 */
@Override public void navigate(boolean requestFocus){
  navigationItem.navigate(requestFocus);
}

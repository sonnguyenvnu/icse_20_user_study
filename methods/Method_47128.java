/** 
 * method called when list item is clicked in the adapter
 * @param isBackButton is it the back button aka '..'
 * @param position the position
 * @param e the list item
 * @param imageView the check {@link RoundedImageView} that is to be animated
 */
public void onListItemClicked(boolean isBackButton,int position,LayoutElementParcelable e,ImageView imageView){
  if (results) {
    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
    SearchWorkerFragment fragment=(SearchWorkerFragment)fragmentManager.findFragmentByTag(MainActivity.TAG_ASYNC_HELPER);
    if (fragment != null) {
      if (fragment.mSearchAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
        fragment.mSearchAsyncTask.cancel(true);
      }
      getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
    mRetainSearchTask=true;
    results=false;
  }
 else {
    mRetainSearchTask=false;
    MainActivityHelper.SEARCH_TEXT=null;
  }
  if (selection) {
    if (isBackButton) {
      selection=false;
      if (mActionMode != null)       mActionMode.finish();
      mActionMode=null;
    }
 else {
      adapter.toggleChecked(position,imageView);
    }
  }
 else {
    if (isBackButton) {
      goBackItemClick();
    }
 else {
      if (getMainActivity().getAppbar().getSearchView().isEnabled()) {
        getMainActivity().getAppbar().getSearchView().hideSearchView();
      }
      String path=!e.hasSymlink() ? e.desc : e.symlink;
      if (e.isDirectory) {
        computeScroll();
        loadlist(path,false,openMode);
      }
 else       if (e.desc.endsWith(CryptUtil.CRYPT_EXTENSION)) {
        isEncryptOpen=true;
        encryptBaseFile=new HybridFileParcelable(getActivity().getExternalCacheDir().getPath() + "/" + e.generateBaseFile().getName().replace(CryptUtil.CRYPT_EXTENSION,""));
        encryptBaseFiles.add(encryptBaseFile);
        EncryptDecryptUtils.decryptFile(getContext(),getMainActivity(),ma,openMode,e.generateBaseFile(),getActivity().getExternalCacheDir().getPath(),utilsProvider,true);
      }
 else {
        if (getMainActivity().mReturnIntent) {
          returnIntentResults(e.generateBaseFile());
        }
 else {
switch (e.getMode()) {
case SMB:
            launchSMB(e.generateBaseFile(),getMainActivity());
          break;
case SFTP:
        Toast.makeText(getContext(),getResources().getString(R.string.please_wait),Toast.LENGTH_LONG).show();
      SshClientUtils.launchSftp(e.generateBaseFile(),getMainActivity());
    break;
case OTG:
  FileUtils.openFile(OTGUtil.getDocumentFile(e.desc,getContext(),false),(MainActivity)getActivity(),sharedPref);
break;
case DROPBOX:
case BOX:
case GDRIVE:
case ONEDRIVE:
Toast.makeText(getContext(),getResources().getString(R.string.please_wait),Toast.LENGTH_LONG).show();
CloudUtil.launchCloud(e.generateBaseFile(),openMode,getMainActivity());
break;
default :
FileUtils.openFile(new File(e.desc),(MainActivity)getActivity(),sharedPref);
break;
}
dataUtils.addHistoryFile(e.desc);
}
}
}
}
}

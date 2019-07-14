/** 
 * Returns the intent with uri corresponding to specific  {@link HybridFileParcelable} back to external app
 */
public void returnIntentResults(HybridFileParcelable baseFile){
  getMainActivity().mReturnIntent=false;
  Intent intent=new Intent();
  if (getMainActivity().mRingtonePickerIntent) {
    Uri mediaStoreUri=MediaStoreHack.getUriFromFile(baseFile.getPath(),getActivity());
    Log.d(getClass().getSimpleName(),mediaStoreUri.toString() + "\t" + MimeTypes.getMimeType(baseFile.getPath(),baseFile.isDirectory()));
    intent.setDataAndType(mediaStoreUri,MimeTypes.getMimeType(baseFile.getPath(),baseFile.isDirectory()));
    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI,mediaStoreUri);
    getActivity().setResult(FragmentActivity.RESULT_OK,intent);
    getActivity().finish();
  }
 else {
    Log.d("pickup","file");
    Intent intentresult=new Intent();
    Uri resultUri=Utils.getUriForBaseFile(getActivity(),baseFile);
    intentresult.setAction(Intent.ACTION_SEND);
    intentresult.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    if (resultUri != null)     intentresult.setDataAndType(resultUri,MimeTypes.getExtension(baseFile.getPath()));
    getActivity().setResult(FragmentActivity.RESULT_OK,intentresult);
    getActivity().finish();
  }
}

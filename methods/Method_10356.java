/** 
 * Returns contents of `custom_ca.txt` file from assets as CharSequence.
 * @return contents of custom_ca.txt file
 */
private CharSequence getReadmeText(){
  String rtn="";
  try {
    InputStream stream=getResources().openRawResource(R.raw.custom_ca);
    java.util.Scanner s=new java.util.Scanner(stream).useDelimiter("\\A");
    rtn=s.hasNext() ? s.next() : "";
  }
 catch (  Resources.NotFoundException e) {
    Log.e(LOG_TAG,"License couldn't be retrieved",e);
  }
  return rtn;
}

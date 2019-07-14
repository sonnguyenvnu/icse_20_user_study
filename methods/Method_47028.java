/** 
 * Copy the dummy image and dummy mp3 into the private folder, if not yet there. Required for the Kitkat workaround.
 * @return the dummy mp3.
 */
private static File copyDummyFiles(Context c){
  try {
    copyDummyFile(R.mipmap.ic_launcher,"mkdirFiles","albumart.jpg",c);
    return copyDummyFile(R.raw.temptrack,"mkdirFiles","temptrack.mp3",c);
  }
 catch (  IOException e) {
    Log.e(LOG,"Could not copy dummy files.",e);
    return null;
  }
}

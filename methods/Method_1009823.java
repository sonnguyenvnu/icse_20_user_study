/** 
 * ???  {@link PhantomServiceManager}?<b>??</b>????????????????
 * @param hostPackage        ????
 * @param hostVersionName    ?????
 * @param hostVersionCode    ?????
 * @param phantomVersionName Phantom ???
 * @param phantomVersionCode Phantom ???
 */
public static synchronized void init(String hostPackage,String hostVersionName,int hostVersionCode,String phantomVersionName,int phantomVersionCode){
  if (sInitialized) {
    return;
  }
  sHostPackage=hostPackage;
  sHostVersionName=hostVersionName;
  sHostVersionCode=hostVersionCode;
  sPhantomVersionName=phantomVersionName;
  sPhantomVersionCode=phantomVersionCode;
  sInitialized=true;
}

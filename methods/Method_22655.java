/** 
 * Read an int value.
 * @return int or 0
 * @param rootKey root key
 * @param subKeyName key name
 * @param name value name
 */
static public int getIntValue(REGISTRY_ROOT_KEY rootKey,String subKeyName,String name){
  Advapi32 advapi32=Advapi32.INSTANCE;
  IntByReference pType=new IntByReference();
  IntByReference lpcbData=new IntByReference();
  HKEY handle=openKey(rootKey,subKeyName,WinNT.KEY_READ);
  int ret=0;
  byte[] lpData=new byte[1];
  if (handle != null) {
    if (advapi32.RegQueryValueEx(handle,name,0,pType,lpData,lpcbData) == WinError.ERROR_MORE_DATA) {
      lpData=new byte[lpcbData.getValue()];
      if (advapi32.RegQueryValueEx(handle,name,0,pType,lpData,lpcbData) == WinError.ERROR_SUCCESS) {
        ret=convertBufferToInt(lpData);
      }
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

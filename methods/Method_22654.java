/** 
 * Read a String value.
 * @param rootKey root key
 * @param subKeyName key name
 * @param name value name
 * @throws java.io.UnsupportedEncodingException on error
 * @return String or null
 */
static public String getStringValue(REGISTRY_ROOT_KEY rootKey,String subKeyName,String name) throws UnsupportedEncodingException {
  byte[] lpData=new byte[1];
  Advapi32 advapi32=Advapi32.INSTANCE;
  IntByReference pType=new IntByReference();
  IntByReference lpcbData=new IntByReference();
  HKEY handle=openKey(rootKey,subKeyName,WinNT.KEY_READ);
  String ret=null;
  if (handle != null) {
    if (advapi32.RegQueryValueEx(handle,name,0,pType,lpData,lpcbData) == WinError.ERROR_MORE_DATA) {
      lpData=new byte[lpcbData.getValue()];
      if (advapi32.RegQueryValueEx(handle,name,0,pType,lpData,lpcbData) == WinError.ERROR_SUCCESS) {
        ret=convertBufferToString(lpData);
      }
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

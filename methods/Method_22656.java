/** 
 * Writes a String value.
 * @param rootKey root key
 * @param subKeyName key name
 * @param name value name
 * @param value value
 * @throws java.io.UnsupportedEncodingException on error
 * @return true on success
 */
static public boolean setStringValue(REGISTRY_ROOT_KEY rootKey,String subKeyName,String name,String value) throws UnsupportedEncodingException {
  byte[] data=new byte[value.length() * 2 + 2];
  byte[] src=value.getBytes("UTF-16LE");
  System.arraycopy(src,0,data,0,src.length);
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEY handle=openKey(rootKey,subKeyName,WinNT.KEY_READ | WinNT.KEY_WRITE);
  boolean ret=false;
  if (handle != null) {
    if (advapi32.RegSetValueEx(handle,name,0,WinNT.REG_SZ,data,data.length) == WinError.ERROR_SUCCESS) {
      ret=true;
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

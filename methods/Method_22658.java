/** 
 * Check for existence of a value.
 * @param rootKey root key
 * @param subKeyName key name
 * @param name value name
 * @return true if exists
 */
static public boolean valueExists(REGISTRY_ROOT_KEY rootKey,String subKeyName,String name){
  Advapi32 advapi32=Advapi32.INSTANCE;
  IntByReference pType=new IntByReference();
  IntByReference lpcbData=new IntByReference();
  HKEY handle=openKey(rootKey,subKeyName,WinNT.KEY_READ);
  byte[] lpData=new byte[1];
  boolean ret=false;
  if (handle != null) {
    if (advapi32.RegQueryValueEx(handle,name,0,pType,lpData,lpcbData) != WinError.ERROR_FILE_NOT_FOUND) {
      ret=true;
    }
 else {
      ret=false;
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

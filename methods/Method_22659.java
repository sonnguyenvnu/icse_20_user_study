/** 
 * Create a new key.
 * @param rootKey root key
 * @param parent name of parent key
 * @param name key name
 * @return true on success
 */
static public boolean createKey(REGISTRY_ROOT_KEY rootKey,String parent,String name){
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEYByReference hkResult=new HKEYByReference();
  IntByReference dwDisposition=new IntByReference();
  HKEY handle=openKey(rootKey,parent,WinNT.KEY_READ);
  boolean ret=false;
  if (handle != null) {
    if (advapi32.RegCreateKeyEx(handle,name,0,null,WinNT.REG_OPTION_NON_VOLATILE,WinNT.KEY_READ,null,hkResult,dwDisposition) == WinError.ERROR_SUCCESS) {
      ret=true;
      advapi32.RegCloseKey(hkResult.getValue());
    }
 else {
      ret=false;
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

/** 
 * Opens a key.
 * @param rootKey root key
 * @param subKeyName name of the key
 * @param access access mode
 * @return handle to the key or 0
 */
private static HKEY openKey(REGISTRY_ROOT_KEY rootKey,String subKeyName,int access){
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEY rootKeyHandle=getRegistryRootKey(rootKey);
  HKEYByReference pHandle=new HKEYByReference();
  if (advapi32.RegOpenKeyEx(rootKeyHandle,subKeyName,0,access,pHandle) == WinError.ERROR_SUCCESS) {
    return pHandle.getValue();
  }
 else {
    return null;
  }
}

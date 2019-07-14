/** 
 * Get all sub keys of a key.
 * @param rootKey root key
 * @param parent key name
 * @return array with all sub key names
 */
static public String[] getSubKeys(REGISTRY_ROOT_KEY rootKey,String parent){
  TreeSet<String> subKeys=new TreeSet<String>();
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEY handle=openKey(rootKey,parent,WinNT.KEY_READ);
  char[] lpName=new char[256];
  IntByReference lpcName=new IntByReference(256);
  WinBase.FILETIME lpftLastWriteTime=new WinBase.FILETIME();
  if (handle != null) {
    int dwIndex=0;
    while (advapi32.RegEnumKeyEx(handle,dwIndex,lpName,lpcName,null,null,null,lpftLastWriteTime) == WinError.ERROR_SUCCESS) {
      subKeys.add(new String(lpName,0,lpcName.getValue()));
      lpcName.setValue(256);
      dwIndex++;
    }
    advapi32.RegCloseKey(handle);
  }
  return subKeys.toArray(new String[]{});
}

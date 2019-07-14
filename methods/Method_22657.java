/** 
 * Writes an int value.
 * @return true on success
 * @param rootKey root key
 * @param subKeyName key name
 * @param name value name
 * @param value value
 */
static public boolean setIntValue(REGISTRY_ROOT_KEY rootKey,String subKeyName,String name,int value){
  byte[] data=new byte[4];
  data[0]=(byte)(value & 0xff);
  data[1]=(byte)((value >> 8) & 0xff);
  data[2]=(byte)((value >> 16) & 0xff);
  data[3]=(byte)((value >> 24) & 0xff);
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEY handle=openKey(rootKey,subKeyName,WinNT.KEY_READ | WinNT.KEY_WRITE);
  boolean ret=false;
  if (handle != null) {
    if (advapi32.RegSetValueEx(handle,name,0,WinNT.REG_DWORD,data,data.length) == WinError.ERROR_SUCCESS) {
      ret=true;
    }
    advapi32.RegCloseKey(handle);
  }
  return ret;
}

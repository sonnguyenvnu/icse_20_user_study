/** 
 * Get all values under a key.
 * @param rootKey root key
 * @param key jey name
 * @throws java.io.UnsupportedEncodingException on error
 * @return TreeMap with name and value pairs
 */
static public TreeMap<String,Object> getValues(REGISTRY_ROOT_KEY rootKey,String key) throws UnsupportedEncodingException {
  TreeMap<String,Object> values=new TreeMap<String,Object>(String.CASE_INSENSITIVE_ORDER);
  Advapi32 advapi32=Advapi32.INSTANCE;
  HKEY handle=openKey(rootKey,key,WinNT.KEY_READ);
  char[] lpValueName=new char[16384];
  IntByReference lpcchValueName=new IntByReference(16384);
  IntByReference lpType=new IntByReference();
  byte[] lpData=new byte[1];
  IntByReference lpcbData=new IntByReference();
  if (handle != null) {
    int dwIndex=0;
    int result=0;
    String name;
    do {
      lpcbData.setValue(0);
      result=advapi32.RegEnumValue(handle,dwIndex,lpValueName,lpcchValueName,null,lpType,lpData,lpcbData);
      if (result == WinError.ERROR_MORE_DATA) {
        lpData=new byte[lpcbData.getValue()];
        lpcchValueName=new IntByReference(16384);
        result=advapi32.RegEnumValue(handle,dwIndex,lpValueName,lpcchValueName,null,lpType,lpData,lpcbData);
        if (result == WinError.ERROR_SUCCESS) {
          name=new String(lpValueName,0,lpcchValueName.getValue());
switch (lpType.getValue()) {
case WinNT.REG_SZ:
            values.put(name,convertBufferToString(lpData));
          break;
case WinNT.REG_DWORD:
        values.put(name,convertBufferToInt(lpData));
      break;
default :
    break;
}
}
}
dwIndex++;
}
 while (result == WinError.ERROR_SUCCESS);
advapi32.RegCloseKey(handle);
}
return values;
}

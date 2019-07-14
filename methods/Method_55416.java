/** 
 * Retrieves information about one of the graphics modes for a display device. To retrieve information for all the graphics modes for a display device, make a series of calls to this function.
 * @param lpszDeviceName a pointer to a null-terminated string that specifies the display device about which graphics mode the function will obtain information.<p>This parameter is either  {@code NULL} or a {@link DISPLAY_DEVICE#DeviceName} returned from {@link #EnumDisplayDevices}. A  {@code NULL} value specifies the current displaydevice on the computer that the calling thread is running on.</p>
 * @param iModeNum       indicates the type of information to be retrieved.<p>Graphics mode indexes start at zero. To obtain information for all of a display device's graphics modes, make a series of calls to {@code EnumDisplaySettingsEx}, as follows: Set  {@code iModeNum} to zero for the first call, and increment {@code iModeNum} by one for eachsubsequent call. Continue calling the function until the return value is zero.</p> <p>When you call  {@code EnumDisplaySettingsEx} with {@code iModeNum} set to zero, the operating system initializes and caches information about thedisplay device. When you call  {@code EnumDisplaySettingsEx} with {@code iModeNum} set to a nonzero value, the function returns the information thatwas cached the last time the function was called with  {@code iModeNum} set to zero.</p><p>This value can be a graphics mode index or one of:<br></p><table><tr><td> {@link #ENUM_CURRENT_SETTINGS}</td><td> {@link #ENUM_REGISTRY_SETTINGS}</td></tr></table>
 * @param lpDevMode      a pointer to a {@link DEVMODE} structure into which the function stores information about the specified graphics mode. Before calling{@code EnumDisplaySettingsEx}, set the  {@code dmSize} member to {@link DEVMODE#SIZEOF}, and set the  {@code dmDriverExtra} member to indicate the size, inbytes, of the additional space available to receive private driver data. <p>The  {@code EnumDisplaySettingsEx} function will populate the {@code dmFields} member of the {@code lpDevMode} and one or more other members of the{@code DEVMODE} structure. To determine which members were set by the call to {@code EnumDisplaySettingsEx}, inspect the  {@code dmFields} bitmask.</p>
 * @param dwFlags        this parameter can be one of:<br><table><tr><td>{@link #EDS_RAWMODE}</td><td> {@link #EDS_ROTATEDMODE}</td></tr></table>
 */
@NativeType("BOOL") public static boolean EnumDisplaySettingsEx(@Nullable @NativeType("LPCTSTR") CharSequence lpszDeviceName,@NativeType("DWORD") int iModeNum,@NativeType("DEVMODE *") DEVMODE lpDevMode,@NativeType("DWORD") int dwFlags){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF16Safe(lpszDeviceName,true);
    long lpszDeviceNameEncoded=lpszDeviceName == null ? NULL : stack.getPointerAddress();
    return nEnumDisplaySettingsEx(lpszDeviceNameEncoded,iModeNum,lpDevMode.address(),dwFlags) != 0;
  }
  finally {
    stack.setPointer(stackPointer);
  }
}

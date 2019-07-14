/** 
 * Changes the settings of the specified display device to the specified graphics mode.
 * @param lpszDeviceName a pointer to a null-terminated string that specifies the display device whose graphics mode will change. Only display device names as returned by{@link #EnumDisplayDevices} are valid.<p>The  {@code lpszDeviceName} parameter can be {@code NULL}. A  {@code NULL} value specifies the default display device. The default device can be determined bycalling  {@code EnumDisplayDevices} and checking for the {@link GDI32#DISPLAY_DEVICE_PRIMARY_DEVICE} flag.</p>
 * @param lpDevMode      a pointer to a {@link DEVMODE} structure that describes the new graphics mode. If {@code lpDevMode} is {@code NULL}, all the values currently in the registry will be used for the display setting. Passing  {@code NULL} for the {@code lpDevMode} parameter and 0 for the {@code dwFlags} parameter is the easiest wayto return to the default mode after a dynamic mode change. <p>The  {@code dmSize} member must be initialized to the size, in bytes, of the {@code DEVMODE} structure. The {@code dmDriverExtra} member must beinitialized to indicate the number of bytes of private driver data following the  {@code DEVMODE} structure.</p>
 * @param hwnd           reserved; must be {@code NULL}
 * @param dwflags        indicates how the graphics mode should be changed. One of:<br><table><tr><td>{@link #CDS_UPDATEREGISTRY}</td><td> {@link #CDS_TEST}</td><td> {@link #CDS_FULLSCREEN}</td><td> {@link #CDS_GLOBAL}</td><td> {@link #CDS_SET_PRIMARY}</td></tr><tr><td> {@link #CDS_VIDEOPARAMETERS}</td><td> {@link #CDS_ENABLE_UNSAFE_MODES}</td><td> {@link #CDS_DISABLE_UNSAFE_MODES}</td><td> {@link #CDS_RESET}</td><td> {@link #CDS_RESET_EX}</td></tr><tr><td> {@link #CDS_NORESET}</td></tr></table>
 * @param lParam         if {@code flags} is {@link #CDS_VIDEOPARAMETERS},  {@code lParam} is a pointer to a {@code VIDEOPARAMETERS} structure. Otherwise {@code lParam} must be {@code NULL}.
 * @return one of the following values: {@link #DISP_CHANGE_SUCCESSFUL} {@link #DISP_CHANGE_RESTART} {@link #DISP_CHANGE_FAILED} {@link #DISP_CHANGE_BADMODE} {@link #DISP_CHANGE_NOTUPDATED} {@link #DISP_CHANGE_BADFLAGS} {@link #DISP_CHANGE_BADPARAM} {@link #DISP_CHANGE_BADDUALVIEW}
 */
@NativeType("LONG") public static int ChangeDisplaySettingsEx(@Nullable @NativeType("LPCTSTR") CharSequence lpszDeviceName,@Nullable @NativeType("DEVMODE *") DEVMODE lpDevMode,@NativeType("HWND") long hwnd,@NativeType("DWORD") int dwflags,@NativeType("LPVOID") long lParam){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF16Safe(lpszDeviceName,true);
    long lpszDeviceNameEncoded=lpszDeviceName == null ? NULL : stack.getPointerAddress();
    return nChangeDisplaySettingsEx(lpszDeviceNameEncoded,memAddressSafe(lpDevMode),hwnd,dwflags,lParam);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}

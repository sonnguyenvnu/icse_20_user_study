/** 
 * Obtains information about the display devices in the current session.
 * @param lpDevice        the device name. If {@code NULL}, function returns information for the display adapter(s) on the machine, based on  {@code devNum}.
 * @param iDevNum         an index value that specifies the display device of interest.<p>The operating system identifies each display device in the current session with an index value. The index values are consecutive integers, starting at 0. If the current session has three display devices, for example, they are specified by the index values 0, 1, and 2.</p>
 * @param lpDisplayDevice a pointer to a {@link DISPLAY_DEVICE} structure that receives information about the display device specified by {@code iDevNum}. <p>Before calling  {@code EnumDisplayDevices}, you must initialize the  {@code cb} member of {@code DISPLAY_DEVICE} to the size, in bytes, of{@code DISPLAY_DEVICE}.</p>
 * @param dwFlags         set this flag to {@link #EDD_GET_DEVICE_INTERFACE_NAME} to retrieve the device interface name for {@code GUID_DEVINTERFACE_MONITOR}, which is registered by the operating system on a per monitor basis. The value is placed in the  {@code DeviceID} member of the {@link DISPLAY_DEVICE} structure returned in{@code lpDisplayDevice}. The resulting device interface name can be used with SetupAPI functions and serves as a link between GDI monitor devices and SetupAPI monitor devices.
 */
@NativeType("BOOL") public static boolean EnumDisplayDevices(@Nullable @NativeType("LPCTSTR") ByteBuffer lpDevice,@NativeType("DWORD") int iDevNum,@NativeType("PDISPLAY_DEVICE") DISPLAY_DEVICE lpDisplayDevice,@NativeType("DWORD") int dwFlags){
  if (CHECKS) {
    checkNT2Safe(lpDevice);
  }
  return nEnumDisplayDevices(memAddressSafe(lpDevice),iDevNum,lpDisplayDevice.address(),dwFlags) != 0;
}

/** 
 * Unsafe version of  {@link #handle_win32_handle(long) handle_win32_handle}. 
 */
public static void nhandle_win32_handle(long struct,long value){
  memPutAddress(struct + CUDA_EXTERNAL_SEMAPHORE_HANDLE_DESC.HANDLE_WIN32_HANDLE,value);
}

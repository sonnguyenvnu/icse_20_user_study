/** 
 * Query capture status of a stream. <p>Query the capture status of a stream and and get an id for the capture sequence, which is unique over the lifetime of the process.</p> <p>If called on  {@link CU70#CU_STREAM_LEGACY STREAM_LEGACY} (the "null stream") while a stream not created with {@link CU#CU_STREAM_NON_BLOCKING STREAM_NON_BLOCKING} is capturing, returns{@link CU#CUDA_ERROR_STREAM_CAPTURE_IMPLICIT}.</p> <p>A valid id is returned only if both of the following are true:</p> <ul> <li>the call returns  {@link NVRTC#NVRTC_SUCCESS SUCCESS}</li> <li> {@code captureStatus} is set to {@link CU100#CU_STREAM_CAPTURE_STATUS_ACTIVE STREAM_CAPTURE_STATUS_ACTIVE}</li> </ul>
 */
@NativeType("CUresult") public static int cuStreamGetCaptureInfo(@NativeType("CUstream") long hStream,@NativeType("CUstreamCaptureStatus *") IntBuffer captureStatus,@NativeType("cuuint64_t *") LongBuffer id){
  if (CHECKS) {
    check(captureStatus,1);
    check(id,1);
  }
  return ncuStreamGetCaptureInfo(hStream,memAddress(captureStatus),memAddress(id));
}

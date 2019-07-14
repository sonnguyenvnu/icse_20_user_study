private static void sendWebpErrorLog(String message){
  if (mWebpErrorLogger != null) {
    mWebpErrorLogger.onWebpErrorLog(message,"decoding_failure");
  }
}

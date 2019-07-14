@TargetApi(Build.VERSION_CODES.O) @DoNotOptimize boolean shouldUseHardwareBitmapConfig(Bitmap.Config config){
  return config == Bitmap.Config.HARDWARE;
}

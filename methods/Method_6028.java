@TargetApi(17) private static void getDisplaySizeV17(Display display,Point outSize){
  display.getRealSize(outSize);
}

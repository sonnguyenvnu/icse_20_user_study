public static void log(final int type,final String tag,final Object... contents){
  if (!CONFIG.mLogSwitch || (!CONFIG.mLog2ConsoleSwitch && !CONFIG.mLog2FileSwitch))   return;
  int type_low=type & 0x0f, type_high=type & 0xf0;
  if (type_low < CONFIG.mConsoleFilter && type_low < CONFIG.mFileFilter)   return;
  final TagHead tagHead=processTagAndHead(tag);
  String body=processBody(type_high,contents);
  if (CONFIG.mLog2ConsoleSwitch && type_low >= CONFIG.mConsoleFilter && type_high != FILE) {
    print2Console(type_low,tagHead.tag,tagHead.consoleHead,body);
  }
  if ((CONFIG.mLog2FileSwitch || type_high == FILE) && type_low >= CONFIG.mFileFilter) {
    print2File(type_low,tagHead.tag,tagHead.fileHead + body);
  }
}

void checkForFontAndColors(){
  try {
    @SuppressLint("SdCardPath") File fontFile=new File("/data/data/com.termux/files/home/.termux/font.ttf");
    @SuppressLint("SdCardPath") File colorsFile=new File("/data/data/com.termux/files/home/.termux/colors.properties");
    final Properties props=new Properties();
    if (colorsFile.isFile()) {
      try (InputStream in=new FileInputStream(colorsFile)){
        props.load(in);
      }
     }
    TerminalColors.COLOR_SCHEME.updateWith(props);
    TerminalSession session=getCurrentTermSession();
    if (session != null && session.getEmulator() != null) {
      session.getEmulator().mColors.reset();
    }
    updateBackgroundColor();
    final Typeface newTypeface=(fontFile.exists() && fontFile.length() > 0) ? Typeface.createFromFile(fontFile) : Typeface.MONOSPACE;
    mTerminalView.setTypeface(newTypeface);
  }
 catch (  Exception e) {
    Log.e(EmulatorDebug.LOG_TAG,"Error in checkForFontAndColors()",e);
  }
}

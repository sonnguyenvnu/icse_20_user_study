public Single<String> convert(final String path,final String encoding){
  return Single.create(emitter -> {
    boolean jadInstall=false;
    String pathToJad=null;
    String pathToJar=path;
    tmpDir.mkdir();
    String targetJarName=pathToJar.substring(pathToJar.lastIndexOf('/') + 1);
    ACRA.getErrorReporter().putCustomData("Last installed app",targetJarName);
    Log.d(TAG,"doInBackground$ pathToJar=" + pathToJar);
    String extension=pathToJar.substring(pathToJar.lastIndexOf('.'));
    if (extension.equalsIgnoreCase(".jad")) {
      jadInstall=true;
      pathToJad=pathToJar;
      pathToJar=pathToJar.substring(0,pathToJar.length() - 1).concat("r");
    }
    File conf=null;
    if (jadInstall) {
      conf=new File(pathToJad);
    }
    File inputJar=new File(pathToJar);
    if (jadInstall && !inputJar.exists()) {
      String url=FileUtils.loadManifest(conf).get("MIDlet-Jar-URL");
      try {
        download(url,inputJar);
      }
 catch (      IOException e) {
        inputJar.delete();
        deleteTemp();
        throw new ConverterException("Can't download jar",e);
      }
    }
    File patchedJar;
    try {
      patchedJar=patchJar(inputJar,encoding);
    }
 catch (    Exception e) {
      deleteTemp();
      throw new ConverterException("Can't patch",e);
    }
    try {
      ZipUtils.unzip(patchedJar,tmpDir);
    }
 catch (    IOException e) {
      deleteTemp();
      throw new ConverterException("Invalid jar",e);
    }
    if (!jadInstall) {
      conf=findManifest(tmpDir);
      if (conf == null) {
        deleteTemp();
        throw new ConverterException("Manifest not found");
      }
    }
    LinkedHashMap<String,String> params=FileUtils.loadManifest(conf);
    appDirPath=params.get("MIDlet-Name");
    if (appDirPath == null) {
      deleteTemp();
      throw new ConverterException("Invalid manifest");
    }
    appDirPath=appDirPath.replace(":","").replace("/","");
    appConverted=new File(Config.APP_DIR,appDirPath);
    FileUtils.deleteDirectory(appConverted);
    appConverted.mkdirs();
    Log.d(TAG,"appConverted=" + appConverted.getPath());
    try {
      Main.main(new String[]{"--no-optimize","--output=" + appConverted.getPath() + Config.MIDLET_DEX_FILE,patchedJar.getAbsolutePath()});
    }
 catch (    IOException e) {
      deleteTemp();
      FileUtils.deleteDirectory(appConverted);
      throw new ConverterException("Can't convert",e);
    }
    try {
      FileUtils.copyFileUsingChannel(conf,new File(appConverted,Config.MIDLET_MANIFEST_FILE));
      File image=new File(tmpDir,AppUtils.getImagePathFromManifest(params));
      FileUtils.copyFileUsingChannel(image,new File(appConverted,Config.MIDLET_ICON_FILE));
    }
 catch (    IOException|NullPointerException e) {
      e.printStackTrace();
    }
catch (    ArrayIndexOutOfBoundsException e) {
      deleteTemp();
      FileUtils.deleteDirectory(appConverted);
      throw new ConverterException("Invalid manifest");
    }
    FileUtils.copyFileUsingChannel(inputJar,new File(appConverted,Config.MIDLET_RES_FILE));
    deleteTemp();
    emitter.onSuccess(appDirPath);
  }
);
}

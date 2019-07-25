public static void setup(){
  if (!hasSet.compareAndSet(false,true)) {
    return;
  }
  String dataBaseDir;
  if (checkDataDirValid(dataBaseDir=DATA_DIR_PATH_PREFIX + PRIMARY_INSTALLER_PACKAGE_NAME + "/")) {
    INSTALLER_PACKAGE_NAME=PRIMARY_INSTALLER_PACKAGE_NAME;
    INSTALLER_DATA_BASE_DIR=dataBaseDir;
    Utils.logI("using " + PRIMARY_INSTALLER_PACKAGE_NAME + "as installer app");
    return;
  }
  if (checkDataDirValid(dataBaseDir=DATA_DIR_PATH_PREFIX + SECONDARY_INSTALLER_PACKAGE_NAME + "/")) {
    INSTALLER_PACKAGE_NAME=SECONDARY_INSTALLER_PACKAGE_NAME;
    INSTALLER_DATA_BASE_DIR=dataBaseDir;
    Utils.logI("using " + SECONDARY_INSTALLER_PACKAGE_NAME + "as installer app");
    return;
  }
  if (checkDataDirValid(dataBaseDir=DATA_DIR_PATH_PREFIX + LEGACY_INSTALLER_PACKAGE_NAME + "/")) {
    INSTALLER_PACKAGE_NAME=LEGACY_INSTALLER_PACKAGE_NAME;
    INSTALLER_DATA_BASE_DIR=dataBaseDir;
    Utils.logI("using " + LEGACY_INSTALLER_PACKAGE_NAME + "as installer app");
    return;
  }
  Utils.logE("no supported installer app found");
}

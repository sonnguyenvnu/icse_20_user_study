/** 
 * Enables colors on *nix systems - not windows. Color support depends on the pmd.color property, which should be set with the -D option during execution - a set value other than 'false' or '0' enables color. <p/> btw, is it possible to do this on windows (ie; console colors)?
 */
private void initializeColorsIfSupported(){
  if (isPropertyEnabled(getProperty(COLOR)) || isPropertyEnabled(System.getProperty(SYSTEM_PROPERTY_PMD_COLOR))) {
    this.yellowBold="\u001B[1;33m";
    this.whiteBold="\u001B[1;37m";
    this.redBold="\u001B[1;31m";
    this.red="\u001B[0;31m";
    this.green="\u001B[0;32m";
    this.cyan="\u001B[0;36m";
    this.colorReset="\u001B[0m";
  }
}

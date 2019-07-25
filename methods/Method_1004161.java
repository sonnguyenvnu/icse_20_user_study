/** 
 * Generates PackagedInjectors and return the generated.
 */
public void generate(){
  messager.printMessage(Kind.NOTE,String.format("%s.generate() for %s",TAG,topLevelPackageString));
  generateAll();
  if (!errors.isEmpty()) {
    messager.printMessage(Kind.ERROR,"Generating injectors failed: ");
    for (    String s : errors) {
      messager.printMessage(Kind.ERROR,s);
    }
  }
}

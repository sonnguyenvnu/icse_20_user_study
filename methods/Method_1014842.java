/** 
 * @param ns Namespace which contains parsed commandline arguments
 * @return true if the sort was successful, false if an error occurred
 */
@Override public boolean process(Namespace ns){
  File file=ns.get("input");
  Object jsonObject=JoltCliUtilities.readJsonInput(file,SUPPRESS_OUTPUT);
  if (jsonObject == null) {
    return false;
  }
  Sortr sortr=new Sortr();
  Object output=sortr.transform(jsonObject);
  Boolean uglyPrint=ns.getBoolean("u");
  return JoltCliUtilities.printJsonObject(output,uglyPrint,SUPPRESS_OUTPUT);
}

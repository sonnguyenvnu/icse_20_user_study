/** 
 * Returns true if the compilation is targeting Android. 
 */
public boolean isAndroidCompatible(){
  return Options.instance(context).getBoolean("androidCompatible");
}

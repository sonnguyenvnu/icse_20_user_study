/** 
 * Enables plugin runtime actions. It is up to developer to the actual work, but base method still checks whether action is available or not.
 */
public final void enable(){
  if (isDisableable()) {
    if (!enabled) {
      enabled();
      enabled=true;
    }
 else {
      Log.warn(this,"Plugin [ " + getPluginInformation() + " ] is already enabled");
    }
  }
 else {
    Log.error(this,"Plugin [ " + getPluginInformation() + " ] cannot be re-enabled");
  }
}

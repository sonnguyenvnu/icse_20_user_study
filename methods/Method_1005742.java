/** 
 * Reload the settings from file if they have changed. <p><strong>Warning:</strong> With enforcing SELinux, this call might be quite expensive.
 */
public synchronized void reload(){
  if (hasFileChanged())   startLoadFromDisk();
}

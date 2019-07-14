/** 
 * <p> Remove the identified <code> {@link TriggerListener}</code> from the <code>Scheduler</code>'s list of <i>internal</i> listeners. </p>
 * @return true if the identified listener was found in the list, andremoved.
 */
public boolean removeinternalTriggerListener(String name){
synchronized (internalTriggerListeners) {
    return (internalTriggerListeners.remove(name) != null);
  }
}

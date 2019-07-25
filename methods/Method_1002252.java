/** 
 * Copy properties of a configuration into this configuration. This method simply iterates the keys of the configuration to be copied and call  {@link #setProperty(String,Object)}for non-null key/value.
 */
@Override public void copy(Configuration c){
  if (c != null) {
    for (Iterator it=c.getKeys(); it.hasNext(); ) {
      String key=(String)it.next();
      Object value=c.getProperty(key);
      if (key != null && value != null) {
        setProperty(key,value);
      }
    }
  }
}

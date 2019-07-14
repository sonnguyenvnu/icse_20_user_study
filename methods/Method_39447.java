/** 
 * Initializes props. By default it only resolves active profiles.
 */
protected void initialize(){
  if (!initialized) {
synchronized (this) {
      if (!initialized) {
        resolveActiveProfiles();
        initialized=true;
      }
    }
  }
}

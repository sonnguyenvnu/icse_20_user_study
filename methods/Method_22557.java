/** 
 * Singleton constructor 
 */
static public Language init(){
  if (instance == null) {
synchronized (Language.class) {
      if (instance == null) {
        instance=new Language();
      }
    }
  }
  return instance;
}

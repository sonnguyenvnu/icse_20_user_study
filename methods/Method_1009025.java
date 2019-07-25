/** 
 * Method init
 * @param resourceBundle
 */
public static synchronized void init(ResourceBundle resourceBundle){
  if (alreadyInitialized) {
    return;
  }
  I18n.resourceBundle=resourceBundle;
  alreadyInitialized=true;
}

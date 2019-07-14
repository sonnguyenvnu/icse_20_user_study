/** 
 * init fxml when loaded.
 */
@PostConstruct public void init(){
  try {
    popup=new JFXPopup(FXMLLoader.load(getClass().getResource("/fxml/ui/popup/DemoPopup.fxml")));
  }
 catch (  IOException ioExc) {
    ioExc.printStackTrace();
  }
  burger1.setOnMouseClicked((e) -> popup.show(rippler1,PopupVPosition.TOP,PopupHPosition.LEFT));
  burger2.setOnMouseClicked((e) -> popup.show(rippler2,PopupVPosition.TOP,PopupHPosition.RIGHT));
  burger3.setOnMouseClicked((e) -> popup.show(rippler3,PopupVPosition.BOTTOM,PopupHPosition.LEFT));
  burger4.setOnMouseClicked((e) -> popup.show(rippler4,PopupVPosition.BOTTOM,PopupHPosition.RIGHT));
}

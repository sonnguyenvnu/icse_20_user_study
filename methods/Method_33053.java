/** 
 * @param maximized
 */
public void setMaximized(boolean maximized){
  if (this.maximized != maximized) {
    Platform.runLater(() -> {
      btnMax.fire();
    }
);
  }
}

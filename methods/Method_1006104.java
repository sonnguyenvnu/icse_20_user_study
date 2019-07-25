@Subscribe public void listen(ConnectionLostEvent connectionLostEvent){
  jabRefFrame.getDialogService().notify(Localization.lang("Connection lost."));
  ButtonType reconnect=new ButtonType(Localization.lang("Reconnect"),ButtonData.YES);
  ButtonType workOffline=new ButtonType(Localization.lang("Work offline"),ButtonData.NO);
  ButtonType closeLibrary=new ButtonType(Localization.lang("Close library"),ButtonData.CANCEL_CLOSE);
  Optional<ButtonType> answer=dialogService.showCustomButtonDialogAndWait(AlertType.WARNING,Localization.lang("Connection lost"),Localization.lang("The connection to the server has been terminated."),reconnect,workOffline,closeLibrary);
  if (answer.isPresent()) {
    if (answer.get().equals(reconnect)) {
      jabRefFrame.closeCurrentTab();
      new SharedDatabaseLoginDialogView(jabRefFrame).showAndWait();
    }
 else     if (answer.get().equals(workOffline)) {
      connectionLostEvent.getBibDatabaseContext().convertToLocalDatabase();
      jabRefFrame.refreshTitleAndTabs();
      jabRefFrame.getDialogService().notify(Localization.lang("Working offline."));
    }
  }
 else {
    jabRefFrame.closeCurrentTab();
  }
}

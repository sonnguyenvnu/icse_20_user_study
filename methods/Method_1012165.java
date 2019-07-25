private void reschedule(){
  Application application=ApplicationManager.getApplication();
  application.invokeLater(this::update,ModalityState.NON_MODAL,application.getDisposed());
}

@Override protected void onPostExecute(AsyncTaskResult<SSHClient> result){
  if (result.exception != null) {
    if (SocketException.class.isAssignableFrom(result.exception.getClass()) || SocketTimeoutException.class.isAssignableFrom(result.exception.getClass())) {
      Toast.makeText(AppConfig.getInstance(),AppConfig.getInstance().getResources().getString(R.string.ssh_connect_failed,hostname,port,result.exception.getLocalizedMessage()),Toast.LENGTH_LONG).show();
    }
 else     if (TransportException.class.isAssignableFrom(result.exception.getClass())) {
      DisconnectReason disconnectReason=TransportException.class.cast(result.exception).getDisconnectReason();
      if (DisconnectReason.HOST_KEY_NOT_VERIFIABLE.equals(disconnectReason)) {
        new AlertDialog.Builder(AppConfig.getInstance().getMainActivityContext()).setTitle(R.string.ssh_connect_failed_host_key_changed_title).setMessage(R.string.ssh_connect_failed_host_key_changed_message).setPositiveButton(R.string.ok,(dialog,which) -> dialog.dismiss()).show();
      }
    }
 else     if (password != null) {
      Toast.makeText(AppConfig.getInstance(),R.string.ssh_authentication_failure_password,Toast.LENGTH_LONG).show();
    }
 else     if (privateKey != null) {
      Toast.makeText(AppConfig.getInstance(),R.string.ssh_authentication_failure_key,Toast.LENGTH_LONG).show();
    }
  }
}

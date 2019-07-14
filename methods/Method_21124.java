private void showBuildAlert(final @NonNull InternalBuildEnvelope envelope){
  new AlertDialog.Builder(this).setTitle(this.upgradeAppString).setMessage(this.aNewerBuildIsAvailableString).setPositiveButton(android.R.string.yes,(dialog,which) -> {
    final Intent intent=new Intent(this,DownloadBetaActivity.class).putExtra(IntentKey.INTERNAL_BUILD_ENVELOPE,envelope);
    startActivity(intent);
  }
).setNegativeButton(android.R.string.cancel,(dialog,which) -> {
  }
).setIcon(android.R.drawable.ic_dialog_alert).show();
}

/** 
 * Creates and injects a wallet fragment into the activity.
 */
private void prepareWalletFragment(){
  final WalletFragmentOptions walletFragmentOptions=WalletFragmentOptions.newBuilder().setEnvironment(AndroidPayUtils.environment(this.build)).setTheme(WalletConstants.THEME_LIGHT).setMode(WalletFragmentMode.BUY_BUTTON).build();
  this.walletFragment=SupportWalletFragment.newInstance(walletFragmentOptions);
  getSupportFragmentManager().beginTransaction().replace(R.id.masked_wallet_fragment,this.walletFragment).commit();
}

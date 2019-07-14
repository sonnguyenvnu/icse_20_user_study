/** 
 * Attempts to programmatically trigger an Android Pay sheet from a wallet fragment. It does this by crawling the subviews of the fragment and clicking them. NB: This is very hacky.
 */
public static void triggerAndroidPaySheet(final @NonNull SupportWalletFragment walletFragment){
  try {
    final ViewGroup group=(ViewGroup)walletFragment.getView();
    if (group != null) {
      recursiveClickFirstChildView(group);
    }
  }
 catch (  ClassCastException|NullPointerException ignored) {
  }
}

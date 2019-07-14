public static boolean isNewGuestCheckoutUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && NEW_GUEST_CHECKOUT_PATTERN.matcher(UriUtilsKt.path(uri)).matches();
}

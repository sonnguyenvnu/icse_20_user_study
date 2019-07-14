/** 
 * Returns an observable of push notification envelopes from the intent data. This will emit only when the project is launched from a push notification.
 */
public static @NonNull Observable<PushNotificationEnvelope> pushNotificationEnvelope(final @NonNull Intent intent){
  return Observable.just(intent.getParcelableExtra(IntentKey.PUSH_NOTIFICATION_ENVELOPE)).ofType(PushNotificationEnvelope.class);
}

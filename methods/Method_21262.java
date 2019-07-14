/** 
 * Returns a  {@link RefTag} observable. If there is no parceled RefTag, emit `null`.
 */
public static @NonNull Observable<RefTag> refTag(final @NonNull Intent intent){
  return Observable.just(intent.getParcelableExtra(IntentKey.REF_TAG));
}

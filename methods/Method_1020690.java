/** 
 * Compose operators fluently via a function callback that returns a Nono for this Nono.
 * @param composer the function receiving this and returns a Nono
 * @return the Nono returned by the function
 */
public final Nono compose(Function<? super Nono,? extends Nono> composer){
  return to(composer);
}

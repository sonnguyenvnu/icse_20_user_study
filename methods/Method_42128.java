/** 
 * return only the first element if there is one
 * @param q
 * @param cr
 * @param ch
 * @param < T >
 * @return
 */
public static <T>Observable<T> querySingle(Query q,ContentResolver cr,CursorHandler<T> ch){
  return Observable.create(subscriber -> {
    Cursor cursor=null;
    try {
      cursor=q.getCursor(cr);
      if (cursor != null && cursor.moveToFirst())       subscriber.onNext(ch.handle(cursor));
      subscriber.onComplete();
    }
 catch (    Exception err) {
      subscriber.onError(err);
    }
 finally {
      if (cursor != null)       cursor.close();
    }
  }
);
}

public final <B,C>Validation<List<E>,C> accumulate(Validation<E,B> v2,F2<T,B,C> f){
  List<E> list=List.nil();
  if (isFail()) {
    list=list.cons(fail());
  }
  if (v2.isFail()) {
    list=list.cons(v2.fail());
  }
  if (!list.isEmpty()) {
    return fail(list);
  }
 else {
    return success(f.f(success(),v2.success()));
  }
}

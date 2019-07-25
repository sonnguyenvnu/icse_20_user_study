public static Action<Object> action(final Closure<?> closure){
  final Closure<?> copy=closure.rehydrate(null,closure.getOwner(),closure.getThisObject());
  return new NoDelegateClosureAction(copy);
}

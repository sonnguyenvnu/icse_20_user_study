public final Option<A> max(){
  return isEmpty() ? none() : r().max().orElse(some(head()));
}

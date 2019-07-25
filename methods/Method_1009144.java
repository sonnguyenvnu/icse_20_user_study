/** 
 * Return a frozen version of this builder
 * @return a new {@link Library}
 * @see Library#Library(LibraryBuilder)
 */
@Override public Library freeze(){
  return new Library(this);
}

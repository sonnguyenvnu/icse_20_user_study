/** 
 * Destructure and apply this product to a function accepting the same number of arguments as this product's slots. This can be thought of as a kind of dual to uncurrying a function and applying a product to it.
 * @param fn  the function to apply
 * @param < R > the return type of the function
 * @return the result of applying the destructured product to the function
 */
default <R>R into(Fn3<? super _1,? super _2,? super _3,? extends R> fn){
  return Product2.super.into(fn).apply(_3());
}

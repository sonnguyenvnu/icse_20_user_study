/** 
 * Constructs an  {@code ImmutableSet} from the first {@code n} elements of the specified array. If{@code k} is the size of the returned {@code ImmutableSet}, then the unique elements of  {@code elements} will be in the first {@code k} positions, and {@code elements[i] == null} for {@code k <= i < n}. <p>This may modify  {@code elements}. Additionally, if  {@code n == elements.length} and {@code elements} contains no duplicates, {@code elements} may be used without copying in the returned{@code ImmutableSet}, in which case it may no longer be modified. <p> {@code elements} may contain only values of type {@code E}.
 * @throws NullPointerException if any of the first {@code n} elements of {@code elements} is null
 */
private static <E>ImmutableSet<E> construct(int n,int expectedSize,Object... elements){
switch (n) {
case 0:
    return of();
case 1:
  @SuppressWarnings("unchecked") E elem=(E)elements[0];
return of(elem);
default :
SetBuilderImpl<E> builder=new RegularSetBuilderImpl<E>(expectedSize);
for (int i=0; i < n; i++) {
@SuppressWarnings("unchecked") E e=(E)checkNotNull(elements[i]);
builder=builder.add(e);
}
return builder.review().build();
}
}

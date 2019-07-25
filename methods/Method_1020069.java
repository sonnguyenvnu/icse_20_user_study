/** 
 * Focus on the head of an  {@link HList}.
 * @param < Head > the head element type
 * @param < Tail > the tail HList type
 * @return a lens that focuses on the head of an HList
 */
public static <Head,Tail extends HList>Lens.Simple<HCons<Head,? extends Tail>,Head> head(){
  return simpleLens(HCons::head,(hCons,newHead) -> cons(newHead,hCons.tail()));
}

/** 
 * Choose between the covariantly-positioned carrier type and the contravariantly-positioned carrier type. This can be used to encode partial functions <code>a -&gt; (_|_ v b)</code> as total functions <code>a -&gt; (a v b)</code>.
 * @return the profunctor with a choice
 */
default Cocartesian<A,Choice2<A,B>,P> choose(){
  return this.<A>cocartesian().contraMap(Choice2::b);
}

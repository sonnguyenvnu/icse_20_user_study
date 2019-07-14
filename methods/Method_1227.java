/** 
 * Initializes  {@link SimpleDraweeView} with supplier of Drawee controller builders. 
 */
public static void initialize(Supplier<? extends AbstractDraweeControllerBuilder> draweeControllerBuilderSupplier){
  sDraweecontrollerbuildersupplier=draweeControllerBuilderSupplier;
}

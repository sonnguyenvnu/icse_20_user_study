/** 
 * Generates new  {@link LayoutSpecModel} from the given {@link PsiClass}.
 * @return new {@link LayoutSpecModel} or null if provided class is null or given class is not a{@link com.facebook.litho.annotations.LayoutSpec} class.
 */
@Nullable public static LayoutSpecModel createLayoutModel(@Nullable PsiClass layoutSpecCls){
  if (layoutSpecCls == null) {
    return null;
  }
  return MODEL_FACTORY.createWithPsi(layoutSpecCls.getProject(),layoutSpecCls,null);
}

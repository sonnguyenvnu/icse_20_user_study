/** 
 * Create a  {@link Builder} that can be used to configure a {@link SectionTree}.
 * @param context The {@link SectionContext} taht will be used to create the child{@link com.facebook.litho.Component}s
 * @param target The {@link Target} that will be responsible to apply the{@link ChangeSet} to the UI.
 */
public static Builder create(SectionContext context,Target target){
  return new Builder(context,target);
}

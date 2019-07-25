/** 
 * Creates filter that combines all other filters.
 * @return filter that combines all other filters
 */
public static IFilter all(){
  return new Filters(new EnumFilter(),new SyntheticFilter(),new SynchronizedFilter(),new TryWithResourcesJavac11Filter(),new TryWithResourcesJavacFilter(),new TryWithResourcesEcjFilter(),new FinallyFilter(),new PrivateEmptyNoArgConstructorFilter(),new StringSwitchJavacFilter(),new StringSwitchEcjFilter(),new EnumEmptyConstructorFilter(),new AnnotationGeneratedFilter(),new KotlinGeneratedFilter(),new KotlinLateinitFilter(),new KotlinWhenFilter(),new KotlinWhenStringFilter(),new KotlinUnsafeCastOperatorFilter(),new KotlinNotNullOperatorFilter(),new KotlinDefaultArgumentsFilter(),new KotlinInlineFilter(),new KotlinCoroutineFilter());
}

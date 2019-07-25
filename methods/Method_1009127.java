/** 
 * Return a thawed instance out of this frozen configuration
 * @return a {@link ValidationConfigurationBuilder}
 * @see ValidationConfigurationBuilder#ValidationConfigurationBuilder(ValidationConfiguration)
 */
@Override public ValidationConfigurationBuilder thaw(){
  return new ValidationConfigurationBuilder(this);
}

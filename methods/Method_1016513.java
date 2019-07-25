/** 
 * Sets the value to be returned by  {@link TypeUsage#scope()}.
 * @return this {@code Builder} object
 * @throws NullPointerException if {@code scope} is null
 */
public TypeUsage.Builder scope(QualifiedName scope){
  this.scope=Objects.requireNonNull(scope);
  return (TypeUsage.Builder)this;
}

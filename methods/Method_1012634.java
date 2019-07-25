/** 
 * @return A new {@link ExecutableInfoBuilder} initialized with the valuesof this  {@link ExecutableInfo}. When done modifying, convert the {@link ExecutableInfoBuilder} into a new {@link ExecutableInfo}instance with  {@link ExecutableInfoBuilder#build()}.
 */
public ExecutableInfoBuilder modify(){
  return new ExecutableInfoBuilder(this);
}

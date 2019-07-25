/** 
 * <p>Supplies the available generators to this one.</p> <p>This is intended for use only by junit-quickcheck itself, and not by creators of custom generators.</p>
 * @param provided repository of available generators
 */
public void provide(Generators provided){
  repo=provided;
}

/** 
 * Adds a decorator to this decorated visitor.
 * @param decorator The decorator to add
 */
public void decorateWith(JavaParserVisitorDecorator decorator){
  decorator.setBase(visitor);
  visitor=decorator;
}

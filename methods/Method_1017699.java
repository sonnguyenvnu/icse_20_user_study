/** 
 * <p>Configures this generator using annotations from a given annotated type.</p> <p>This method considers only annotations that are themselves marked with  {@link GeneratorConfiguration}.</p> <p>By default, the generator will configure itself using this procedure:</p> <ul> <li>For each of the given annotations: <ul> <li>Find a  {@code public} method on the generator named{@code configure}, that accepts a single parameter of the annotation type</li> <li>Invoke the  {@code configure} method reflectively, passing theannotation as the argument</li> </ul> </li> </ul>
 * @param annotatedType a type usage
 * @throws GeneratorConfigurationException if the generator does not"understand" one of the generation configuration annotations on the annotated type
 */
public void configure(AnnotatedType annotatedType){
  configureStrict(collectConfigurationAnnotations(annotatedType));
}

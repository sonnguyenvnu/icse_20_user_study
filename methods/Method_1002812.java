/** 
 * Adds the  {@link DocServiceFilter} that checks whether a method will be <b>excluded</b> while building{@link DocService}. The  {@link DocServiceFilter} will be invoked with the plugin, service andmethod name. The rule is as follows: <ul> <li>No  {@link #include(DocServiceFilter)} and {@link #exclude(DocServiceFilter)} is called -include all methods.</li> <li>Only  {@link #exclude(DocServiceFilter)} is called -include all methods except the methods which the exclusion filter returns  {@code true}.</li> <li>Only  {@link #include(DocServiceFilter)} is called -include the methods which the inclusion filter returns  {@code true}.</li> <li> {@link #include(DocServiceFilter)} and {@link #exclude(DocServiceFilter)} is called -include the methods which the inclusion filter returns  {@code true} and the exclusion filterreturns  {@code false}.</li> </ul> <P>Note that this can be called multiple times and the  {@link DocServiceFilter}s are composed using {@link DocServiceFilter#or(DocServiceFilter)} and {@link DocServiceFilter#and(DocServiceFilter)}.
 * @see #exclude(DocServiceFilter)
 * @see DocService to find out how DocService generates documentaion
 */
public DocServiceBuilder exclude(DocServiceFilter filter){
  requireNonNull(filter,"filter");
  if (excludeFilter == NO_SERVICE) {
    excludeFilter=filter;
  }
 else {
    excludeFilter=excludeFilter.or(filter);
  }
  return this;
}

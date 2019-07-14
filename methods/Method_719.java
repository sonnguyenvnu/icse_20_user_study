/** 
 * Set JSONP request parameter names. Each time a request has one of those parameters, the resulting JSON will be wrapped into a function named as specified by the JSONP request parameter value. <p>The parameter names configured by default are "jsonp" and "callback".
 * @see <a href="http://en.wikipedia.org/wiki/JSONP">JSONP Wikipedia article</a>
 * @since 4.1
 */
public void setJsonpParameterNames(Set<String> jsonpParameterNames){
  Assert.notEmpty(jsonpParameterNames,"jsonpParameterName cannot be empty");
  this.jsonpParameterNames=jsonpParameterNames.toArray(new String[jsonpParameterNames.size()]);
}

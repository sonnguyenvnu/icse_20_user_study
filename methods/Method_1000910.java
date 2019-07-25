/** 
 * Method that can be used for merging default values from `this` instance with specified overrides; and either return `this` if overrides had no effect (that is, result would be equal), or a new instance with merged visibility settings.
 */
protected VisibilityChecker _with(Visibility f,Visibility g,Visibility isG,Visibility s,Visibility cr,Visibility scalarCr){
  if ((f == _fieldMinLevel) && (g == _getterMinLevel) && (isG == _isGetterMinLevel) && (s == _setterMinLevel) && (cr == _creatorMinLevel) && (scalarCr == _scalarConstructorMinLevel)) {
    return this;
  }
  return new VisibilityChecker(f,g,isG,s,cr,scalarCr);
}

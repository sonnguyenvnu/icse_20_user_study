@SuppressWarnings("unchecked") private void _explode(Collection<PropertyName> newNames,Map<PropertyName,POJOPropertyBuilder> props,Linked<?> accessors){
  final Linked<?> firstAcc=accessors;
  for (Linked<?> node=accessors; node != null; node=node.next) {
    PropertyName name=node.name;
    if (!node.isNameExplicit || name == null) {
      if (!node.isVisible) {
        continue;
      }
      throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name '" + _name + "'): found multiple explicit names: " + newNames + ", but also implicit accessor: " + node);
    }
    POJOPropertyBuilder prop=props.get(name);
    if (prop == null) {
      prop=new POJOPropertyBuilder(_config,_annotationIntrospector,_forSerialization,_internalName,name);
      props.put(name,prop);
    }
    if (firstAcc == _fields) {
      Linked<AnnotatedField> n2=(Linked<AnnotatedField>)node;
      prop._fields=n2.withNext(prop._fields);
    }
 else     if (firstAcc == _getters) {
      Linked<AnnotatedMethod> n2=(Linked<AnnotatedMethod>)node;
      prop._getters=n2.withNext(prop._getters);
    }
 else     if (firstAcc == _setters) {
      Linked<AnnotatedMethod> n2=(Linked<AnnotatedMethod>)node;
      prop._setters=n2.withNext(prop._setters);
    }
 else     if (firstAcc == _ctorParameters) {
      Linked<AnnotatedParameter> n2=(Linked<AnnotatedParameter>)node;
      prop._ctorParameters=n2.withNext(prop._ctorParameters);
    }
 else {
      throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
    }
  }
}

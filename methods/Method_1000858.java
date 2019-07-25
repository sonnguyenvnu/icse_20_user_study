/** 
 * Method for constructing a  {@link BeanDeserializer}, given all information collected.
 */
public JsonDeserializer<?> build(){
  Collection<SettableBeanProperty> props=_properties.values();
  _fixAccess(props);
  if (_objectIdReader != null) {
    props=_addIdProp(_properties,new ObjectIdValueProperty(_objectIdReader,PropertyMetadata.STD_REQUIRED));
  }
  BeanPropertyMap propertyMap=BeanPropertyMap.construct(props,_config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES),_collectAliases(props));
  boolean anyViews=!_config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
  if (!anyViews) {
    for (    SettableBeanProperty prop : props) {
      if (prop.hasViews()) {
        anyViews=true;
        break;
      }
    }
  }
  return new BeanDeserializer(this,_beanDesc,propertyMap,_backRefProperties,_ignorableProps,_ignoreAllUnknown,anyViews);
}

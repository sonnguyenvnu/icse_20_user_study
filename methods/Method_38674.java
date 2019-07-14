/** 
 * Defines how JSON parser works. In non-lazy mode, the whole JSON is parsed as it is. In the lazy mode, not everything is parsed, but some things are left lazy. This way we gain performance, especially on partial usage of the whole JSON. However, be aware that parser holds the input memory until the returned objects are disposed.
 */
public JsonParser lazy(final boolean lazy){
  this.lazy=lazy;
  this.mapSupplier=lazy ? LAZYMAP_SUPPLIER : HASHMAP_SUPPLIER;
  this.listSupplier=lazy ? LAZYLIST_SUPPLIER : ARRAYLIST_SUPPLIER;
  return this;
}

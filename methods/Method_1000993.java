/** 
 * Need to make a copy on snapshot() to avoid accidental leakage via cache. In theory only needed if there are modifiers, but since these are lightweight objects, let's recreate always.
 */
@Override public TypeFactory snapshot(){
  return new TypeFactory(_typeCache.snapshot(),_modifiers,_classLoader);
}

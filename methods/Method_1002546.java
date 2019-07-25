public ExampleRequestResponse method(ResourceMethod method){
switch (method) {
case OPTIONS:
    return options();
case GET_ALL:
  return getAll();
case GET:
return get();
case CREATE:
return create();
case UPDATE:
return update();
case PARTIAL_UPDATE:
return partialUpdate();
case DELETE:
return delete();
case BATCH_GET:
return batchGet();
case BATCH_CREATE:
return batchCreate();
case BATCH_UPDATE:
return batchUpdate();
case BATCH_PARTIAL_UPDATE:
return batchPartialUpdate();
case BATCH_DELETE:
return batchDelete();
default :
throw new IllegalArgumentException("Unrecognized ResourceMethod value requested, this method only supports core restful " + "methods, finder and action not supported (for those, use finder() or action()).  for method:" + method);
}
}

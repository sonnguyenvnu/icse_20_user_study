@SuppressWarnings("unchecked") private void generate() throws Exception {
  while (!_out.isFull()) {
    DataComplex current=_stack.peek();
    if (_iteratorStack.size() != _stack.size()) {
      if (_typeStack.peek() == MAP) {
        DataMap dataMap=preProcessMap((DataMap)current);
        if (dataMap != null) {
          _iteratorStack.push(createIterator(dataMap));
          writeStartObject();
        }
 else {
          _stack.pop();
          _typeStack.pop();
          _done=_stack.isEmpty();
          if (_done) {
            _generator.close();
            break;
          }
        }
      }
 else {
        DataList dataList=preProcessList((DataList)current);
        if (dataList != null) {
          _iteratorStack.push(createIterator(dataList));
          writeStartArray();
        }
 else {
          _stack.pop();
          _typeStack.pop();
          _done=_stack.isEmpty();
          if (_done) {
            _generator.close();
            break;
          }
        }
      }
      continue;
    }
    Iterator<?> curr=_iteratorStack.peek();
    if (curr.hasNext()) {
      Object currItem=curr.next();
      if (_typeStack.peek() == MAP) {
        Map.Entry<String,?> entry=(Map.Entry<String,?>)currItem;
        writeFieldName(entry.getKey());
        writeValue(entry.getValue());
      }
 else {
        writeValue(currItem);
      }
    }
 else {
      _stack.pop();
      _iteratorStack.pop();
      Object type=_typeStack.pop();
      if (type == MAP) {
        writeEndObject();
      }
 else {
        writeEndArray();
      }
      _done=_stack.isEmpty();
      if (_done) {
        _generator.close();
        break;
      }
    }
  }
}

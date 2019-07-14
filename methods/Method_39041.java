/** 
 * Registration main point. Does two things: <ul> <li> {@link jodd.madvoc.component.ActionMethodParser#parse(Class,java.lang.reflect.Method,ActionDefinition) parse action}and creates  {@link ActionRuntime}</li> <li> {@link #registerActionRuntime(ActionRuntime) registers} created {@link ActionRuntime}</li> </ul> Returns created  {@link ActionRuntime}.
 * @see #registerActionRuntime(ActionRuntime)
 */
public ActionRuntime registerAction(final Class actionClass,final Method actionMethod,final ActionDefinition actionDefinition){
  final ActionRuntime actionRuntime=actionMethodParser.parse(actionClass,actionMethod,actionDefinition);
  if (actionRuntime == null) {
    return null;
  }
  return registerActionRuntime(actionRuntime);
}

/** 
 * Builds  {@link PathMacros action path macros} from given actionpath chunks. Returns either <code>null</code>, if no action path contains no macros, or instance of the <code>PathMacro</code> implementations.
 */
public PathMacros buildActionPathMacros(final String actionPath){
  if (actionPath.isEmpty()) {
    return null;
  }
  PathMacros pathMacros=createPathMacroInstance();
  if (!pathMacros.init(actionPath,actionsManager.getPathMacroSeparators())) {
    return null;
  }
  return pathMacros;
}

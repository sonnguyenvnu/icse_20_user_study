/*
 * Copyright 2003-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.ide.findusages.view.treeholder.tree;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.IExternalizeable;
import jetbrains.mps.ide.findusages.model.SearchResult;
import jetbrains.mps.ide.findusages.model.SearchResults;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.BaseNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.MainNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ModelNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ModuleNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.NodeNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ResultsNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.SearchedNodesNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.INodeRepresentator;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItem;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItemRole;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathProvider;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.NameUtil;
import jetbrains.mps.util.Pair;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModuleReference;

import javax.swing.Icon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DataTree implements IExternalizeable, IChangeListener {
  private BaseNodeData myTreeRoot = new MainNodeData(PathItemRole.ROLE_MAIN_ROOT);
  private final List<IChangeListener> myListeners = new ArrayList<>(2);
  private final DataTreeChangesNotifier myChangesNotifier;

  //this is only used in 3.2 to make rebuild faster in case of many nodes.
  //in 3.3 it will be fixed by introducing path providers
  //this cache is only alive during read action in build() method
  private Map<Pair<BaseNodeData, Object>, BaseNodeData> myRebuildCache;

  public DataTree(@NotNull DataTreeChangesNotifier changeDispatch) {
    myChangesNotifier = changeDispatch;
  }

  public BaseNodeData getTreeRoot() {
    return myTreeRoot;
  }

  public BaseNodeData getSearchSubtree() {
    return myTreeRoot.children().findFirst().orElse(null);
  }

  public BaseNodeData getResultsSubtree() {
    return myTreeRoot.children().skip(1).findFirst().orElse(null);
  }


  //----EXCLUSION/EXPANSION----

  public void setExcluded(Set<BaseNodeData> nodes, boolean value) {
    for (BaseNodeData node : nodes) {
      setExcludedRecursively(nodes, node, value);
    }
    checkExcluded();
    notifyChangeListeners();
  }

  //doNotProcess is needed as there might be many pairs of nodes in the list, one of which is a child of another
  private static void setExcludedRecursively(final Set<BaseNodeData> doNotProcess, BaseNodeData node, final boolean value) {
    node.setExcluded(value);
    final Predicate<BaseNodeData> contains = doNotProcess::contains;
    node.children().filter(contains.negate()).forEach(child -> setExcludedRecursively(doNotProcess, child, value));
  }

  private void checkExcluded() {
    checkNodeExcluded(myTreeRoot);
  }

  private static void checkNodeExcluded(BaseNodeData node) {
    if (!node.hasChildren()) {
      return;
    }
    node.children().forEach(DataTree::checkNodeExcluded);
    boolean allChildrenExcluded = node.children().allMatch(BaseNodeData::isExcluded);
    node.setExcluded(allChildrenExcluded);
  }

  //----CONTENT MANAGEMENT----

  public void setContents(SearchResults results, INodeRepresentator nodeRepresentator) {
    setContents(build(results, nodeRepresentator));
  }

  protected void setContents(MainNodeData root) {
    myTreeRoot = root;
    stopListening();
    startListening();
    notifyChangeListeners();
  }

  private void startListening() {
    HashSet<SNodeReference> nodes = new HashSet<>();
    HashSet<SModelReference> models = new HashSet<>();
    HashSet<SModuleReference> modules = new HashSet<>();

    nodes.addAll(Arrays.asList(getNodeDataStream(myTreeRoot).filter(nd -> nd instanceof NodeNodeData).map(
        nd -> ((NodeNodeData) nd).getNodePointer()).toArray(SNodeReference[]::new)));
    models.addAll(Arrays.asList(getNodeDataStream(myTreeRoot).filter(nd -> nd instanceof ModelNodeData).map(
        nd -> ((ModelNodeData) nd).getModelReference()).toArray(SModelReference[]::new)));

    modules.addAll(Arrays.asList(getNodeDataStream(myTreeRoot).filter(nd -> nd instanceof ModuleNodeData).map(
        nd -> ((ModuleNodeData) nd).getModuleReference()).toArray(SModuleReference[]::new)));

    myChangesNotifier.trackNodes(this, nodes);
    myChangesNotifier.trackModels(this, models);
    myChangesNotifier.trackModules(this, modules);
  }

  public static Stream<BaseNodeData> getNodeDataStream(BaseNodeData d) {
    return Stream.concat(Stream.of(d), d.children().flatMap(DataTree::getNodeDataStream));
  }

  private void stopListening() {
    myChangesNotifier.unregister(this);
  }

  public void dispose() {
    stopListening();
  }


  //----TREE BUILD STUFF----

  private MainNodeData build(final SearchResults<?> results, final INodeRepresentator nodeRepresentator) {
    myRebuildCache = new HashMap<>();

    MainNodeData root = new MainNodeData(PathItemRole.ROLE_MAIN_ROOT);

    SearchedNodesNodeData nodesRoot = new SearchedNodesNodeData(PathItemRole.ROLE_MAIN_SEARCHED_NODES);
    root.addChild(nodesRoot);
    // XXX null INodeRepresentator in PP, below, is important as we don't want to use custom presentation for look up elements, just for results
    //     not that I fully understand or approve the idea, it's the way it used to be for years.
    final PathProvider pp1 = new PathProvider(null, false);
    for (Object node : results.getSearchedObjects().getElements()) {
      if (node != null) {
        createPath(pp1, nodesRoot, new SearchResult<>(node, SearchedNodesNodeData.CATEGORY_NAME));
      }
    }

    final List<? extends SearchResult<?>> notNullResults = results.getNotNullResults();
    final Icon i;
    final String c;
    if (nodeRepresentator == null) {
      i = null; // use default
      c = NameUtil.formatNumericalString(notNullResults.size(), "usage") + " found";
    } else {
      i = nodeRepresentator.getResultsIcon();
      c = nodeRepresentator.getResultsText(new TextOptions(true, false, notNullResults.size()));
    }
    ResultsNodeData resultsRoot = new ResultsNodeData(PathItemRole.ROLE_MAIN_RESULTS, i, c);
    root.addChild(resultsRoot);
    final PathProvider pp2 = new PathProvider(nodeRepresentator, true);
    for (SearchResult<?> result : notNullResults) {
      createPath(pp2, resultsRoot, result);
    }

    myRebuildCache = null;
    return root;
  }

  // XXX has to build the tree without duplications, e.g. there could be no duplicated model elements under same path(category).
  private void createPath(@NotNull PathProvider pathProvider, @NotNull BaseNodeData parent, @NotNull SearchResult result) {

    final List<PathItem<?>> path = pathProvider.getPathForSearchResult(result);

    // empty path likely means there are search result with path elements we don't recognize
    assert !path.isEmpty();
    final PathItem pathTail = path.get(path.size() - 1);

    for (PathItem currentPathItem : path) {
      Object currentIdObject = currentPathItem.getIdObject();

      final Pair<BaseNodeData, Object> key = new Pair<>(parent, currentIdObject);
      BaseNodeData seen = myRebuildCache.get(key);

      if (seen == null) {

        BaseNodeData data = currentPathItem.create();

        parent.addChild(data);
        // XXX beware, currentIdObject != data.getIdObject() at least for CategoryNodeData; use former as it's the one use to query the cache
        myRebuildCache.put(key, data);
        parent = data;
      } else {
        // FWIW, there's PathItem.isTail() now
        final boolean isPathTail = currentPathItem == pathTail;
        if (isPathTail) {
          // XXX why some flag and not another node to represent the result, with use of
          // nodeRepresentator.getPresentation(searchResult.getObject()) as it's for other result nodes?
          seen.setIsPathTail_internal(true);
        }
        parent = seen;
      }
    }
  }

  //----READ/WRITE STUFF----

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    if (element.getChild("data") != null) {
      // FIXME drop once 2019.2 is out, no reason to account for pre-2019.2 format
      return;
    }
    myTreeRoot.read(element, project);
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    myTreeRoot.write(element, project);
  }

  //----LISTENERS STUFF----

  public void addChangeListener(IChangeListener listener) {
    myListeners.add(listener);
  }

  public void removeChangeListeners(IChangeListener listener) {
    myListeners.remove(listener);
  }

  public void notifyChangeListeners() {
    for (IChangeListener listener : myListeners) {
      listener.changed();
    }
  }

  @Override
  public void changed() {
    notifyChangeListeners();
  }
}

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
package jetbrains.mps.ide.findusages.findalgorithm.resultproviders.treenodes;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.findalgorithm.finders.CollectingCallback;
import jetbrains.mps.ide.findusages.findalgorithm.finders.IFinder.FindCallback;
import jetbrains.mps.ide.findusages.findalgorithm.finders.SearchedObjects;
import jetbrains.mps.ide.findusages.model.IResultProvider;
import jetbrains.mps.ide.findusages.model.SearchQuery;
import jetbrains.mps.ide.findusages.model.SearchResult;
import jetbrains.mps.ide.findusages.model.SearchResults;
import jetbrains.mps.progress.EmptyProgressMonitor;
import jetbrains.mps.project.Project;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NOTE: all nodes except UnionNode MUST have <2 children
 */
public abstract class BaseNode implements IResultProvider {
  private static final Logger LOG = LogManager.getLogger(BaseNode.class);
  private static final String CHILD = "child";
  private static final String CHILD_CLASS = "rpn"; // Result Provider Node
  protected BaseNode myParent;
  protected List<BaseNode> myChildren = new ArrayList<>();

  public BaseNode() {

  }

  //----TREE STUFF----

  public BaseNode getParent() {
    return myParent;
  }

  public void setParent(BaseNode parent) {
    myParent = parent;
  }

  public void addChild(BaseNode child) {
    myChildren.add(child);
    child.setParent(this);
  }

  public void removeChild(BaseNode child) {
    assert child.getParent() == this;
    myChildren.remove(child);
    child.setParent(null);
  }

  public void clearChildren() {
    myChildren.clear();
  }

  public List<BaseNode> getChildren() {
    return Collections.unmodifiableList(myChildren);
  }

  public boolean isRoot() {
    return myParent == null;
  }

  //----SEARCH STUFF----

  @Override
  public void findResults(@NotNull SearchQuery query, @NotNull FindCallback callback, @Nullable ProgressMonitor monitor) {
    if (monitor == null) {
      monitor = new EmptyProgressMonitor();
    }
    doFindResults(query, new FindCallback() {
      @Override
      public void onUsageFound(@NotNull SearchResult<?> result) {
        if (result.getObject() == null) {
          LOG.error("#getSearchResults returned results containing null, which means that some of your filters and finders is incorrect");
        } else {
          callback.onUsageFound(result);
        }
      }

      @Override
      public void onSearchedObjectsCalculated(@NotNull SearchedObjects<?> searchedObjects) {
        if (searchedObjects.contains(null)) {
          LOG.error("#getSearchedObjects returned nodes containing null, which means that some of your filters and finders is incorrect");
        } else {
          callback.onSearchedObjectsCalculated(searchedObjects);
        }
      }
    }, monitor);
  }

  @NotNull
  @Override
  public SearchResults getResults(@NotNull SearchQuery query, @Nullable ProgressMonitor monitor) {
//    assert !ThreadUtils.isEventDispatchThread();

    CollectingCallback callback = new CollectingCallback();
    findResults(query, callback, monitor);
    return callback.getResults();
  }

  protected abstract void doFindResults(@NotNull SearchQuery query, @NotNull FindCallback callback, @NotNull ProgressMonitor monitor);

  @Override
  public long getEstimatedTime(SearchScope scope) {
    long sumTime = 0;
    for (BaseNode child : myChildren) {
      sumTime = sumTime + child.getEstimatedTime(scope);
    }
    return sumTime;
  }

  //----SAVE/LOAD STUFF----

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    for (BaseNode child : myChildren) {
      Element childXML = new Element(CHILD);
      childXML.setAttribute(CHILD_CLASS, child.getClass().getName());
      child.write(childXML, project);
      element.addContent(childXML);
    }
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    for (Element childXML : element.getChildren(CHILD)) {
      try {
        BaseNode child = (BaseNode) Class.forName(childXML.getAttributeValue(CHILD_CLASS)).newInstance();
        child.read(childXML, project);
        myChildren.add(child);
        child.setParent(this);
      } catch (Throwable t) {
        throw new CantLoadSomethingException("Error while instantiating node: " + t.getMessage(), t);
      }
    }
  }
}

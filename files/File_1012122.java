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
package jetbrains.mps.ide.findusages.view.optionseditor.options;

import gnu.trove.THashSet;
import jetbrains.mps.ide.findusages.FindersManager;
import jetbrains.mps.ide.findusages.findalgorithm.finders.IInterfacedFinder;
import jetbrains.mps.ide.findusages.model.IResultProvider;
import jetbrains.mps.ide.findusages.view.FindUtils;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class FindersOptions extends BaseOptions {
  private static final String FINDERS = "finders";
  private static final String FINDER = "finder";
  private static final String CLASS_NAME = "class_name";

  @NotNull
  private List<String> myFindersClassNames = new ArrayList<>();

  public FindersOptions(Element element, Project project) {
    read(element, project);
  }

  public FindersOptions(String... findersClassNames) {
    myFindersClassNames.addAll(Arrays.asList(findersClassNames));
  }

  @Override
  public FindersOptions clone() {
    FindersOptions result = new FindersOptions();
    result.myFindersClassNames.addAll(myFindersClassNames);
    return result;
  }

  /**
   * Makes a copy of finder options and additionally enables default finders for the node
   * {@link jetbrains.mps.ide.findusages.findalgorithm.finders.IInterfacedFinder#isUsedByDefault(SNode)}.
   *
   * FIXME Due to legacy/transition issues, finders are also consulted for isVisible+isApplicable
   * ({@code {@link jetbrains.mps.ide.findusages.FindersManager#getAvailableFinders(SNode)}}), although I don't really see a reason for that.
   * I'd rather add those that answer isUsedByDefault only, and filter isVisible/isApplicable later (in UI).
   */
  public FindersOptions cloneWithDefaultForNode(@NotNull SNode node) {
    FindersOptions rv = clone();
    Set<IInterfacedFinder> availableFinders = FindersManager.getInstance().getAvailableFinders(node);
    final Set<String> findersByDefault = new THashSet<>();
    availableFinders.stream().filter(f -> f.isUsedByDefault(node)).map(f -> f.getClass().getName()).forEach(findersByDefault::add);
    // remove duplicates, if any
    findersByDefault.removeAll(rv.myFindersClassNames);
    rv.myFindersClassNames.addAll(findersByDefault);
    return rv;
  }

  @NotNull
  public List<String> getFindersClassNames() {
    return myFindersClassNames;
  }

  public void setFindersClassNames(@NotNull List<String> findersClassNames) {
    myFindersClassNames = findersClassNames;
  }

  public IResultProvider getResult() {
    return FindUtils.makeProvider(myFindersClassNames.toArray(new String[0]));
  }

  @Override
  public void write(Element element, Project project) {
    Element findersXML = new Element(FINDERS);
    for (String finderClassName : myFindersClassNames) {
      Element finderXML = new Element(FINDER);
      finderXML.setAttribute(CLASS_NAME, finderClassName);
      findersXML.addContent(finderXML);
    }
    element.addContent(findersXML);
  }

  @Override
  public void read(Element element, Project project) {
    Element findersXML = element.getChild(FINDERS);
    for (Element finderXML : findersXML.getChildren(FINDER)) {
      String finderName = finderXML.getAttribute(CLASS_NAME).getValue();
      myFindersClassNames.add(finderName);
    }
  }
}

/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.ide.findusages.view.optionseditor;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.IExternalizeable;
import jetbrains.mps.ide.findusages.view.optionseditor.options.FindersOptions;
import jetbrains.mps.ide.findusages.view.optionseditor.options.ScopeOptions;
import jetbrains.mps.ide.findusages.view.optionseditor.options.ViewOptions;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class FindUsagesOptions implements Cloneable {
  public static final String FINDERS = "finders";
  public static final String SCOPE = "scope";
  public static final String VIEW = "view";

  private FindersOptions myFindersOptions = new FindersOptions();
  private ScopeOptions myScopeOptions = new ScopeOptions();
  private ViewOptions myViewOptions = new ViewOptions();

  public FindUsagesOptions(@NotNull FindersOptions findersOptions, @NotNull ScopeOptions scopeOptions, @NotNull ViewOptions viewOptions) {
    myFindersOptions = findersOptions;
    myScopeOptions = scopeOptions;
    myViewOptions = viewOptions;
  }

  public FindUsagesOptions(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  @NotNull
  public FindersOptions getFindersOptions() {
    return myFindersOptions;
  }

  public void setFindersOptions(FindersOptions findersOptions) {
    myFindersOptions = findersOptions;
  }

  @NotNull
  public ScopeOptions getScopeOptions() {
    return myScopeOptions;
  }

  public void setScopeOptions(ScopeOptions scopeOptions) {
    myScopeOptions = scopeOptions;
  }

  @NotNull
  public ViewOptions getViewOptions() {
    return myViewOptions;
  }

  public void setViewOptions(ViewOptions viewOptions) {
    myViewOptions = viewOptions;
  }

  public void read(Element element, Project project) throws CantLoadSomethingException {
    Element optionXML;

    optionXML = element.getChild(FINDERS);
    if (optionXML == null) throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
    myFindersOptions = new FindersOptions(optionXML, project);

    optionXML = element.getChild(SCOPE);
    if (optionXML == null) throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
    myScopeOptions = new ScopeOptions(optionXML, project);

    optionXML = element.getChild(VIEW);
    if (optionXML == null) throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
    myViewOptions = new ViewOptions(optionXML, project);
  }

  public void write(Element element, Project project) throws CantSaveSomethingException {
    writeOption(element, project, FINDERS, myFindersOptions);
    writeOption(element, project, SCOPE, myScopeOptions);
    writeOption(element, project, VIEW, myViewOptions);
  }

  private void writeOption(Element element, Project project, String tagName, IExternalizeable option) throws CantSaveSomethingException {
    Element optionXML = new Element(tagName);
    option.write(optionXML, project);
    element.addContent(optionXML);
  }

  @Override
  protected FindUsagesOptions clone() {
    return new FindUsagesOptions(myFindersOptions, myScopeOptions, myViewOptions);
  }
}

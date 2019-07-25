/*
 * Copyright 2003-2018 JetBrains s.r.o.
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

import jetbrains.mps.ide.findusages.model.scopes.FindUsagesScope;
import jetbrains.mps.ide.findusages.model.scopes.GlobalScope;
import jetbrains.mps.ide.findusages.model.scopes.ModelsScope;
import jetbrains.mps.ide.findusages.model.scopes.ModulesScope;
import jetbrains.mps.ide.findusages.model.scopes.ProjectScope;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModuleRepositoryFacade;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelName;

// FIXME myModel and myModule are DEFAULT_VALUE - why do we serialize this
public class ScopeOptions extends BaseOptions {
  private static final Logger LOG = LogManager.getLogger(ScopeOptions.class);
  private static final String SCOPE_TYPE = "scope_type";
  private static final String MODEL = "model";
  private static final String MODULE = "module";

  @NotNull
  private ScopeType myScopeType;
  private String myModel;
  private String myModule;
  public static final String DEFAULT_VALUE = "<default>";

  public ScopeOptions() {
    myScopeType = ScopeType.GLOBAL;
  }

  public ScopeOptions(Element element, Project project) {
    read(element, project);
  }

  public ScopeOptions(@NotNull ScopeType scopeType, String model, String module) {
    myScopeType = scopeType;
    myModel = model;
    myModule = module;
  }

  @Override
  public ScopeOptions clone() {
    return new ScopeOptions(myScopeType, myModel, myModule);
  }

  @NotNull
  public ScopeType getScopeType() {
    return myScopeType;
  }

  public void setScopeType(@NotNull ScopeType scopeType) {
    myScopeType = scopeType;
  }

  public String getModel() {
    return myModel;
  }

  public void setModel(String model) {
    myModel = model;
  }

  public String getModule() {
    return myModule;
  }

  public void setModule(String module) {
    myModule = module;
  }

  // XXX expects model read despite no SModel/SNode comes in here, only Project
  public FindUsagesScope getScope(final Project project) {
    // XXX as long as we serialize model and module name instead of a respective reference, treat all models/modules with the same name
    //     as scope (despite the fact user may have picked a specific one. OTOH, he may have entered name and its intention is not obvious).
    switch (myScopeType) {
      case GLOBAL:
        return new GlobalScope(project);
      case PROJECT:
        return new ProjectScope(project);
      case MODULE:
        return new ModulesScope(new ModuleRepositoryFacade(project).getModulesByName(myModule));
      case MODEL:
        return new ModelsScope(new ModuleRepositoryFacade(project).getModelsByName(new SModelName(myModel)));
      default:
        LOG.error("Illegal scope type: " + myScopeType);
        return new GlobalScope(project);
    }
  }

  @Override
  public void write(Element element, Project project) {
    Element scopeTypeXML = new Element(SCOPE_TYPE);
    scopeTypeXML.setAttribute(SCOPE_TYPE, myScopeType.name());
    scopeTypeXML.setAttribute(MODULE, myModule == null ? "" : myModule);
    scopeTypeXML.setAttribute(MODEL, myModel == null ? "" : myModel);
    element.addContent(scopeTypeXML);
  }

  @Override
  public void read(Element element, Project project) {
    Element scopeTypeXML = element.getChild(SCOPE_TYPE);
    myScopeType = ScopeType.valueOf(scopeTypeXML.getAttributeValue(SCOPE_TYPE));
    myModule = scopeTypeXML.getAttributeValue(MODULE);
    myModel = scopeTypeXML.getAttributeValue(MODEL);
  }

  public enum ScopeType {
    GLOBAL, PROJECT, MODULE, MODEL
  }
}

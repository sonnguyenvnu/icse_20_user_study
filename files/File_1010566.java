/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.extapi.module;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleFacet;
import org.jetbrains.mps.openapi.persistence.Memento;

/**
 * Base class for all module facets.
 */
public abstract class ModuleFacetBase implements SModuleFacet {
  private final String myFacetType;
  private SModule myModule;
  private boolean isRegistered;

  protected ModuleFacetBase(@NotNull String facetType) {
    myFacetType = facetType;
  }

  @NotNull
  @Override
  public final String getFacetType() {
    return myFacetType;
  }

  public String getFacetPresentation() {
    return getFacetType();
  }

  @NotNull
  @Override
  public SModule getModule() {
    return myModule;
  }

  /**
   * FIXME javadoc @return and do we need both setModule + attach?
   * Returns null if the facet cannot work within the passed module.
   */
  public boolean setModule(SModule module) {
    // FIXME the 'cannot work' logic shall move to FacetFactory.isApplicable()
    checkNotRegistered();
    myModule = module;
    return true;
  }

  protected void checkNotRegistered() {
    if (isRegistered()) {
      throw new IllegalStateException("cannot change properties of the registered root");
    }
  }

  public boolean isRegistered() {
    return isRegistered;
  }

  public void attach() {
    assert myModule != null;
    isRegistered = true;
  }

  public void dispose() {
    isRegistered = false;
  }


  @Override
  public void save(Memento memento) {
    // no-op
  }

  @Override
  public void load(Memento memento) {
    checkNotRegistered();
  }
}

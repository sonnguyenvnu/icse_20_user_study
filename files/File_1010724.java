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
package jetbrains.mps.nodeEditor.cellActions;

import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;

@Deprecated
@ToRemove(version = 2018.3)
//use CellAction_DeleteSReference instead
public class CellAction_DeleteReference extends AbstractCellAction {
  private SNode mySource;
  private String myRole;

  @Deprecated
  @ToRemove(version = 2018.3)
  public CellAction_DeleteReference(SNode source, String role) {
    mySource = source;
    myRole = role;
  }

  public CellAction_DeleteReference(SNode source, SReferenceLink link) {
    this(source, link.getName());
  }

  @Override
  public boolean canExecute(EditorContext context) {
    return true;
  }

  @Override
  public void execute(EditorContext context) {
    SNodeAccessUtil.setReferenceTarget(mySource, myRole, null);
  }
}

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
package jetbrains.mps.typesystem.checking;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.IndexNotReadyException;
import jetbrains.mps.checkers.CheckingSession;
import jetbrains.mps.checkers.ICheckingPostprocessor;
import jetbrains.mps.checkers.LanguageErrorsComponent.ApprovableError;
import jetbrains.mps.errors.IErrorReporter;
import jetbrains.mps.errors.item.EditorQuickFix;
import jetbrains.mps.errors.item.FlavouredItem.ReportItemFlavour;
import jetbrains.mps.errors.item.IssueKindReportItem;
import jetbrains.mps.errors.item.IssueKindReportItem.PathObject;
import jetbrains.mps.errors.item.IssueKindReportItem.PathObject.NodePathObject;
import jetbrains.mps.errors.item.NodeReportItem;
import jetbrains.mps.errors.item.TypesystemReportItemAdapter;
import jetbrains.mps.newTypesystem.context.IncrementalTypecheckingContext;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorMessage;
import jetbrains.mps.nodeEditor.HighlighterMessage;
import jetbrains.mps.nodeEditor.checking.BaseEditorChecker;
import jetbrains.mps.nodeEditor.checking.QuickFixRuntimeEditorWrapper;
import jetbrains.mps.nodeEditor.checking.UpdateResult;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.progress.EmptyProgressMonitor;
import jetbrains.mps.smodel.event.SModelEvent;
import jetbrains.mps.typechecking.backend.TypecheckingSession;
import jetbrains.mps.typesystem.LegacyTypecheckingProvider;
import jetbrains.mps.typesystem.LegacyTypecheckingQueries;
import jetbrains.mps.typesystem.inference.TypeCheckingContext;
import jetbrains.mps.typesystem.inference.TypeContextManager;
import jetbrains.mps.util.Cancellable;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.WeakSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.util.Consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: fyodor
 * Date: 4/30/13
 */
public abstract class AbstractTypesystemEditorChecker extends BaseEditorChecker implements EditorMessageOwner {
  public static boolean IMMEDIATE_QFIX_DISABLED = false;
  private WeakSet<Map<ReportItemFlavour, Object>> myOnceExecutedQuickFixes = new WeakSet<>();
  private boolean myHasEvents = false;
  private final SRepository myRepository;
  private final Collection<ICheckingPostprocessor<NodeReportItem>> myPostprocessors;

  public AbstractTypesystemEditorChecker(SRepository repository, Collection<ICheckingPostprocessor<NodeReportItem>> postprocessors) {
    myRepository = repository;
    myPostprocessors = postprocessors;
  }

  @NotNull
  protected abstract UpdateResult doCreateMessages(TypeCheckingContext context, boolean wasCheckedOnce, EditorContext editorContext,
                                                   SNode rootNode, Cancellable cancellable, boolean applyQuickFixes);

  @Override
  public void processEvents(List<SModelEvent> events) {
    myHasEvents |= !events.isEmpty();
  }

  @Override
  public boolean needsUpdate(EditorComponent editorComponent) {
    return myHasEvents;
  }

  @Override
  public void doneUpdating() {
    myHasEvents = false;
  }

  @NotNull
  @Override
  public UpdateResult update(final EditorComponent editorComponent, final boolean incremental, final boolean applyQuickFixes,
                             final Cancellable cancellable) {
    try {
      TypecheckingSession session = editorComponent.getTypecheckingSession();
      if (session == null) return UpdateResult.CANCELLED;
      
      LegacyTypecheckingQueries legacyTypesystemQueries = session.getQueries(LegacyTypecheckingProvider.class);
      TypeCheckingContext typeCheckingContext = legacyTypesystemQueries.getTypeCheckingContext();
      return ((IncrementalTypecheckingContext) typeCheckingContext).runTypeCheckingAction(() ->
          doCreateMessages(typeCheckingContext, incremental, editorComponent.getEditorContext(),
                           editorComponent.getEditedNode(), cancellable,
                           applyQuickFixes));

    } catch (IndexNotReadyException e) {
      if (editorComponent.getNodeForTypechecking() != null) {
        TypecheckingSession session = editorComponent.getTypecheckingSession();
        LegacyTypecheckingQueries legacyTypesystemQueries = session.getQueries(LegacyTypecheckingProvider.class);
        legacyTypesystemQueries.getTypeCheckingContext().clear();
      }
      throw e;
    }
  }

  protected Collection<EditorMessage> collectMessagesForNodesWithErrors(TypeCheckingContext context, final EditorContext editorContext,
                                                                        boolean typesystemErrors, boolean applyQuickFixes) {
    Map<NodePathObject, Collection<ApprovableError>> errorMap = new HashMap<>();
    Map<SNodeReference, List<NodeReportItem>> postprocessMap = new HashMap<>();
    Consumer<NodeReportItem> postprocessConsumer = nodeReportItem -> {
      postprocessMap.computeIfAbsent(nodeReportItem.getNode(), k -> new ArrayList<>());
      postprocessMap.get(nodeReportItem.getNode()).add(nodeReportItem);
    };
    for (Pair<SNode, List<IErrorReporter>> nodeErrors : context.getNodesWithErrors(typesystemErrors)) {
      List<ApprovableError> nodeErrorList = nodeErrors.o2.stream().map(iErrorReporter -> new ApprovableError(new TypesystemReportItemAdapter(iErrorReporter), true)).collect(Collectors.toList());
      errorMap.put(new NodePathObject(nodeErrors.o1.getReference()), nodeErrorList);
    }
    for (ICheckingPostprocessor<NodeReportItem> postprocessor : myPostprocessors) {
      postprocessor.postProcess(myRepository, new EmptyProgressMonitor(), new CheckingSession<NodeReportItem>() {
        @Override
        public Map<PathObject, ? extends Collection<? extends SuppressableError<? extends IssueKindReportItem>>> getAllFoundErrors() {
          return Collections.unmodifiableMap(errorMap);
        }
        @Override
        public Consumer<? super NodeReportItem> postprocessingConsumer() {
          return postprocessConsumer;
        }
      });
    }
    for (Entry<NodePathObject, Collection<ApprovableError>> nodeErrors : errorMap.entrySet()) {
      for (ApprovableError error : nodeErrors.getValue()) {
        if (error.myApproved) {
          postprocessConsumer.consume(error.getError());
        }
      }
    }

    Set<EditorMessage> messages = new HashSet<>();
    for (Entry<SNodeReference, List<NodeReportItem>> errorNode : postprocessMap.entrySet()) {
      List<NodeReportItem> errors = new ArrayList<>(errorNode.getValue());
      errors.sort((o1, o2) -> o2.getSeverity().compareTo(o1.getSeverity()));
      boolean instantIntentionApplied = false;
      for (NodeReportItem reportItem : errors) {
        HighlighterMessage message = HighlightUtil.createHighlighterMessage(reportItem, AbstractTypesystemEditorChecker.this, editorContext.getRepository());

        EditorQuickFix quickfix = TypesystemReportItemAdapter.FLAVOUR_EDITOR_QUICKFIX.getAutoApplicable(message.getReportItem());
        final SNode quickFixNode = errorNode.getKey().resolve(myRepository);
        if (quickfix != null && applyQuickFixes && !instantIntentionApplied && !AbstractTypesystemEditorChecker.IMMEDIATE_QFIX_DISABLED) {
          instantIntentionApplied = applyInstantIntention(editorContext, quickFixNode, quickfix);
          if (instantIntentionApplied) {
            // skip the message
            continue;
          }
        }

        messages.add(message);
      }
    }
    return messages;
  }

  private boolean applyInstantIntention(final EditorContext editorContext, final SNode quickFixNode,
                                        @NotNull final EditorQuickFix intention) {
    Map<ReportItemFlavour, Object> flavours = new HashMap<>();
    for (ReportItemFlavour<?, ?> flavour : intention.getIdFlavours()) {
      flavours.put(flavour, flavour.tryToGet(intention));
    }
    if (!myOnceExecutedQuickFixes.contains(flavours)) {
      myOnceExecutedQuickFixes.add(flavours);
      // XXX why Application.invokeLater, not ThreadUtils or ModelAccess (likely, shall use SNodeReference for quickFixNode, not SNode, and resolve inside)
      ApplicationManager.getApplication().invokeLater(() -> {
        editorContext.getRepository().getModelAccess().executeUndoTransparentCommand(() -> {
            QuickFixRuntimeEditorWrapper.getInstance(intention).execute(editorContext, true);
        });
      }, ModalityState.NON_MODAL);
    }
    return true;
  }
}

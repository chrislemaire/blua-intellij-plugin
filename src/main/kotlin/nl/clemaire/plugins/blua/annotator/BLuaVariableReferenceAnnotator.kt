package nl.clemaire.plugins.blua.annotator

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaVarRefExpr

class BLuaVariableReferenceAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is BLuaVarRefExpr) {
            return
        }

        // Mark unresolvable variables
        if (element.reference?.resolve() == null && element.text.isNotEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Cannot resolve variable '${element.text}'")
                .range(element.textRange)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create()
        }
    }
}

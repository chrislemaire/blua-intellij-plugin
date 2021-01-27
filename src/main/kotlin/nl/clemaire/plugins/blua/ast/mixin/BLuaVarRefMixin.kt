package nl.clemaire.plugins.blua.ast.mixin

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import nl.clemaire.plugins.blua.ast.BLuaElementFactory
import nl.clemaire.plugins.blua.ast.BLuaParams
import nl.clemaire.plugins.blua.ast.getPreviousScope

abstract class BLuaVarRefMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiReference {
    override fun getReference() =
        this

    override fun getElement(): PsiElement =
        this

    override fun getRangeInElement(): TextRange =
        TextRange.allOf(text)

    override fun resolve(): PsiElement? =
        getPreviousScope()[this.text]

    override fun getCanonicalText(): String =
        text

    override fun isReferenceTo(element: PsiElement): Boolean =
        true

    override fun isSoft(): Boolean =
        false

    override fun bindToElement(element: PsiElement): PsiElement =
        replace(BLuaElementFactory.createVarRef(project, element.text))
    override fun handleElementRename(newElementName: String): PsiElement =
        replace(BLuaElementFactory.createVarRef(project, newElementName))
}

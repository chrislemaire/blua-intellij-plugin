package nl.clemaire.plugins.blua.ast.mixin.misc

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import nl.clemaire.plugins.blua.ast.BLuaElementFactory
import nl.clemaire.plugins.blua.ast.BLuaType
import nl.clemaire.plugins.blua.ast.BLuaTypedId
import nl.clemaire.plugins.blua.ast.getPreviousScope

abstract class BLuaTypedIdMixin(node: ASTNode) : ASTWrapperPsiElement(node), BLuaTypedId {
    override fun getName(): String? = idToken.text

    override fun setName(name: String): PsiElement {
        idToken.replace(BLuaElementFactory.createIdToken(project, name))
        return this
    }

    override fun getNameIdentifier(): PsiElement? = idToken

    override fun getTextOffset(): Int =
        idToken.textOffset

    override fun getUseScope(): SearchScope =
        LocalSearchScope(parent.parent)

    override fun getReference() =
        if (resolve() != null) this
        else null

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

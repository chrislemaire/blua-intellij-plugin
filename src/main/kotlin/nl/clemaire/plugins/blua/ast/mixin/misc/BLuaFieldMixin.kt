package nl.clemaire.plugins.blua.ast.mixin.misc

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaElementFactory
import nl.clemaire.plugins.blua.ast.BLuaField

abstract class BLuaFieldMixin(node: ASTNode) : ASTWrapperPsiElement(node), BLuaField {
    override fun getName(): String? = idToken.text

    override fun setName(name: String): PsiElement {
        idToken.replace(BLuaElementFactory.createIdToken(project, name))
        return this
    }

    override fun getNameIdentifier(): PsiElement? = idToken

    override fun getTextOffset(): Int =
        idToken.textOffset
}
